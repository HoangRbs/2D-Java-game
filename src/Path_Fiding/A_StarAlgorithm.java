package Path_Fiding;

//AI :))))

import BaseMainGame.GameHandler;
import Camera.Camera_2D;
import Tile.Tile;
import Tile.TileInitStuff;
import gfx.Assets;
import gfx.Text;

import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Comparator;

public class A_StarAlgorithm {   //USING THIS ALGORITHM IN WORLD_2D_TILE

    private A_StarAlgorithm()
    {
        //prevent from creating object
    }

    private static ArrayList<Node> NodesTileMap;
    private static Node StartNode;
    private static Node EndNode;
    private static ArrayList<Node> Nodes_ToTest;      //to test unvisited node
    private static Node currentTestingNode;                  //currently accessing node
    private static int WorldTileWidth;
    private static int WorldTileHeight;

    private static float distance(Node a,Node b)     //distance between 2 nodes
    {
        return((float)Math.sqrt(Math.pow(b.x - a.x,2) + Math.pow(b.y - a.y,2)));
    }

    private static float Heuristic(Node a,Node EndNode)
    {
        return distance(a,EndNode);
        //return 1;    //works like dijkstra and will definitely slower
    }

    public static void GenerateNodesTileMap(int _WorldTileWidth,int _WorldTileHeight)  //USE IN GAMESTATE
    {
        NodesTileMap = new ArrayList<Node>();
        Nodes_ToTest = new ArrayList<Node>();
        WorldTileWidth = _WorldTileWidth;
        WorldTileHeight = _WorldTileHeight;

        for(int Yindex = 0; Yindex < WorldTileHeight;Yindex++)
        {
            for(int Xindex = 0 ; Xindex < WorldTileWidth;Xindex++)
            {
                Node currentNode = new Node(Xindex * TileInitStuff.TILE_WIDTH,Yindex * TileInitStuff.TILE_HEIGHT,"");

                //is currentNode obstacle ?
                //get the type of Tile at the specific position
                Tile currentTile = GameHandler.GetInstance().get_World2dTile_object().getTileAt(Yindex,Xindex);

                if(currentTile.isCollidable())
                {
                   currentNode.is_Obstacle = true;
                }

                NodesTileMap.add(currentNode);
            }
        }

        //create connections
        for(int Yindex = 0; Yindex < WorldTileHeight;Yindex++)
        {
            for(int Xindex = 0 ; Xindex < WorldTileWidth;Xindex++) {

                if(Xindex < (WorldTileWidth - 1))
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex + 1 + Yindex*WorldTileWidth));
                if(Xindex > 0)
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex - 1 + Yindex*WorldTileWidth));
                if(Yindex < (WorldTileHeight - 1))
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex + (Yindex + 1)*WorldTileWidth));
                if(Yindex > 0)
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex + (Yindex - 1)*WorldTileWidth));

                //cross connect

                /*
                if(Xindex > 0 && Yindex > 0)
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex - 1 + (Yindex - 1) * WorldTileWidth));
                if(Xindex < (WorldTileWidth - 1) && Yindex > 0)
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex + 1 + (Yindex - 1) * WorldTileWidth));
                if(Xindex < (WorldTileWidth - 1) && Yindex < (WorldTileHeight - 1))
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex + 1 + (Yindex + 1) * WorldTileWidth));
                if(Xindex > 0 && Yindex < (WorldTileHeight - 1))
                    NodesTileMap.get(Xindex + Yindex * WorldTileWidth).Neighbors.add(NodesTileMap.get(Xindex - 1 + (Yindex + 1) * WorldTileWidth));
                */
            }
        }

        //StartNode, EndNode is set Outside
    }



    //setting through position on the Map
    public static void setStart_EndNode(int StartPosX,int StartPosY,int EndPosX,int EndPosY)
    {
        //StartNode
        int Xindex = StartPosX / (TileInitStuff.TILE_WIDTH) ;
        int Yindex = StartPosY / (TileInitStuff.TILE_HEIGHT);

        if(Xindex < 0 || Xindex > WorldTileWidth - 1 || Yindex < 0 || Yindex > WorldTileHeight - 1)
        {
            StartNode = null;
            return;
        }

        //get the type of Tile at the specific position
        Tile currentTile = GameHandler.GetInstance().get_World2dTile_object().getTileAt(Yindex,Xindex);
        if(currentTile.isCollidable())
        {
            StartNode = null;  //just in case when player collide with a Collidable Tile
            //which means collide into a OBSTACLE NODE
            //the A* will visit through the whole Nodes since it cannot find the EndNode
            //which would drop a huge fps in our game
            return;
        }

        StartNode = NodesTileMap.get(Yindex * WorldTileWidth + Xindex);

        //EndNode
        Xindex = EndPosX / (TileInitStuff.TILE_WIDTH) ;
        Yindex = EndPosY / (TileInitStuff.TILE_HEIGHT);

        if(Xindex < 0 || Xindex > WorldTileWidth - 1 || Yindex < 0 || Yindex > WorldTileHeight - 1)
        {
            EndNode = null;
            return;
        }

        EndNode = NodesTileMap.get(Yindex * WorldTileWidth + Xindex);
    }

    //mainly do all the work -- Solving A Star algorithm
    public static void Update()
    {
        if(StartNode == null || EndNode == null)      //we don't wanna do anything if we don't have destination
            return;


        //done solving A* previously
        //reset everything to make A* works every frame and that's how
        //it should be
        Reset();

        //RE INIT
        StartNode.currentCost = 0;
        StartNode.FutureCost = StartNode.currentCost + (int)Heuristic(StartNode,EndNode);
        Nodes_ToTest.add(StartNode);
        StartNode.is_BeingInsideNodesToTest = true;

        while(!Nodes_ToTest.isEmpty() && !Nodes_ToTest.get(0).equals(EndNode))
            //absolute shortest path: only use IF(Nodes_ToTest.isEmpty()) --> it will visit all the nodes
        {

            if(Nodes_ToTest.get(0).equals(EndNode))  //we don't want to check EndNode and go backward
            {
                Nodes_ToTest.remove(0);
            }

            //since NodesToTest remove above --> any code that related to the NodesToTest
            //might caught NULL ERROR since NodeToTest might be empty() at that time
            if(Nodes_ToTest.isEmpty())
                break;

            //set currentNode
            currentTestingNode = Nodes_ToTest.get(0);

            for(Node currentNeighbor : currentTestingNode.Neighbors)  //neighbors of currentNode
            {
                //add to Nodes to Test
                //if not visited and not Being inside (avoid adding the same object in arrayList)
                if(!currentNeighbor.is_Visited &&
                        !currentNeighbor.is_BeingInsideNodesToTest &&
                        !currentNeighbor.is_Obstacle)
                {
                    Nodes_ToTest.add(currentNeighbor);
                    currentNeighbor.is_BeingInsideNodesToTest = true;  //void adding the same object
                                                            //while that object is already BEING inside
                }

                //distance of currentNode and currentNeighbor
                int currentDistance = (int)distance(currentTestingNode,currentNeighbor);

                if((currentTestingNode.currentCost + currentDistance) < currentNeighbor.currentCost
                        || currentNeighbor.currentCost == -1)
                {
                    //Update the currentCost of current Neighbor
                    currentNeighbor.currentCost = currentTestingNode.currentCost + currentDistance;

                    //Update previousNode of neighbor --> which is currentTestingNode that made the currentCost of neighbor
                    currentNeighbor.previousNode = currentTestingNode;

                    //Update the FutureCost of current Neighbor
                    currentNeighbor.FutureCost = currentNeighbor.currentCost + (int)Heuristic(currentNeighbor,EndNode);

                }
            }

            //after accessing all neighbors of currentNode
            //Mark  currentNode as Visited and remove from The List
            currentTestingNode.is_Visited = true;
            currentTestingNode.is_BeingInsideNodesToTest = false; //since it will be remove from
                                                                 //the list
            Nodes_ToTest.remove(currentTestingNode);

            //since NodesToTest remove above --> any code that related to the NodesToTest
            //might caught NULL ERROR since NodeToTest might be empty() at that time
            if(Nodes_ToTest.isEmpty())
                break;

            //sort the NodesToTest list in ascending order --> to get the shortest FutureCost
            //--> that'll be the next currentNode to Test
            Nodes_ToTest.sort(Nodes_ToTest_Comparator);
        }


        //IMPORTANT NOTE :
        //because of our Game.Update() can update() multiple times in 1 frame cuz of
        //my FPS algorithm --> at some point it will Update 3 times just to match the FPS
        // --> then it gets to Render()
        //that's why NodesToTest not Reset().....  (which I put in Render() is a big mistake)
        //.....at the next Update() at some point
    }

    //for nodes To Test sorting
    //COMPARATOR OBJECT
    private static Comparator<Node> Nodes_ToTest_Comparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if(o1.FutureCost < o2.FutureCost)
                return -1;
            return 1;
        }
    };

    //visualizing is mostly for testing
    public static void Render(Graphics m_RealScreenObject)     //to visualize how the algorithm running
    {

        if(StartNode == null || EndNode == null)      //we don't wanna do anything if we don't have destination
            return;

        //When the Update A_StarAlgorithm is done --> means A* is solved
        StartNode.NodeColor = Color.red;
        EndNode.NodeColor = Color.BLACK;

        for(int i =0 ; i < NodesTileMap.size(); i++)
        {
            Node currentNode = NodesTileMap.get(i);

            //if the current Node is visited due to A* --> color it just to watch the efficentcy
            if(currentNode.is_Visited)
                currentNode.NodeColor = Color.blue;  //default is grey

            if(currentNode.is_Obstacle)
                currentNode.NodeColor = Color.magenta;

            //draw node
            currentNode.Render(m_RealScreenObject);

            //draw connection
            for(Node currentNeighbor : currentNode.Neighbors)
            {
                //draw connected Line neighbors
                m_RealScreenObject.setColor(Color.blue);
                m_RealScreenObject.drawLine((int)(currentNode.getCenterX() - Camera_2D.GetInstance().getxOffset()),
                                            (int)(currentNode.getCenterY() - Camera_2D.GetInstance().getyOffset()),
                                            (int)(currentNeighbor.getCenterX() - Camera_2D.GetInstance().getxOffset()),
                                            (int)(currentNeighbor.getCenterY() - Camera_2D.GetInstance().getyOffset()));


            }

        }

        //display the shortest path
        Node currentNode = EndNode;
        m_RealScreenObject.setColor(Color.red);

        while(currentNode.previousNode != null)
        {
            m_RealScreenObject.drawLine((int)(currentNode.getCenterX() - Camera_2D.GetInstance().getxOffset()),
                                        (int)(currentNode.getCenterY() - Camera_2D.GetInstance().getyOffset()),
                                        (int)(currentNode.previousNode.getCenterX() - Camera_2D.GetInstance().getxOffset()),
                                        (int)(currentNode.previousNode.getCenterY() - Camera_2D.GetInstance().getyOffset()));

            currentNode = currentNode.previousNode;
        }

    }

    private static void Reset()      //will be used in Render since Render still need info before reset
    {
        for(Node currentNode : NodesTileMap)
        {
            currentNode.Reset();
        }

        //while(!Nodes_ToTest.isEmpty())
        {
        //    Nodes_ToTest.remove(0);
        }

        Nodes_ToTest.clear();
    }


    public static Node getEndNode()
    {
        return EndNode;
    }



    //FOGOTTEN BULLSHIT CODE AND SHOULD NEVER BE ENABLED
    /*

    public static void GenerateNodes_MapTesting(int MapWidth,int MapHeight)  //after the 2D Tile World is load , for now we'll just gonna
    //create a random map for testing
    {
        //TESTING...........
        NodesTileMap = new ArrayList<Node>();

        StartNode = new Node(200,200,"A");
        NodesTileMap.add(StartNode);

        Node NodeB = new Node(300,140,"B");
        NodesTileMap.add(NodeB);

        Node NodeC = new Node(400,140,"C");
        NodesTileMap.add(NodeC);
        //NodeC.is_Obstacle = true;

        EndNode = new Node(500,200,"D");
        NodesTileMap.add(EndNode);

        Node NodeE = new Node(350,200,"E");
        NodesTileMap.add(NodeE);
        NodeE.is_Obstacle = true;

        Node NodeF = new Node(350,300,"F");
        NodesTileMap.add(NodeF);
        //NodeF.is_Obstacle = true;


        StartNode.NodeColor = Color.red;
        EndNode.NodeColor  = Color.black;

        StartNode.currentCost = 0;
        StartNode.FutureCost = StartNode.currentCost + (int)Heuristic(StartNode,EndNode);

        //create connections
        for(int i = 0; i < NodesTileMap.size(); i++)
        {

            //hard coded for now
            if(NodesTileMap.get(i).Name == "A")
            {
                for(int j = 0; j < NodesTileMap.size();j++)
                {
                    String Name = NodesTileMap.get(j).Name;
                    if(Name == "B" || Name == "E" || Name == "F")
                        NodesTileMap.get(i).Neighbors.add(NodesTileMap.get(j));
                }

            }
            else if(NodesTileMap.get(i).Name == "B")
            {
                for(int j = 0; j < NodesTileMap.size();j++)
                {
                    String Name = NodesTileMap.get(j).Name;
                    if(Name == "A" || Name == "C")
                        NodesTileMap.get(i).Neighbors.add(NodesTileMap.get(j));
                }

            }
            else if(NodesTileMap.get(i).Name == "C")
            {
                for(int j = 0; j < NodesTileMap.size();j++)
                {
                    String Name = NodesTileMap.get(j).Name;
                    if(Name == "B" || Name == "E" || Name == "D")
                        NodesTileMap.get(i).Neighbors.add(NodesTileMap.get(j));
                }
            }
            else if(NodesTileMap.get(i).Name == "D")
            {
                for(int j = 0; j < NodesTileMap.size();j++)
                {
                    String Name = NodesTileMap.get(j).Name;
                    if(Name == "C" || Name == "E" || Name == "F")
                        NodesTileMap.get(i).Neighbors.add(NodesTileMap.get(j));
                }

            }
            else if(NodesTileMap.get(i).Name == "E")
            {
                for(int j = 0; j < NodesTileMap.size();j++)
                {
                    String Name = NodesTileMap.get(j).Name;
                    if(Name == "A" || Name == "F" || Name == "C" || Name == "D")
                        NodesTileMap.get(i).Neighbors.add(NodesTileMap.get(j));
                }

            }
            else if(NodesTileMap.get(i).Name == "F")
            {
                for(int j = 0; j < NodesTileMap.size();j++)
                {
                    String Name = NodesTileMap.get(j).Name;
                    if(Name == "A" || Name == "E" || Name == "D")
                        NodesTileMap.get(i).Neighbors.add(NodesTileMap.get(j));
                }

            }
        }


        //Nodes To Test
        Nodes_ToTest = new ArrayList<Node>();
    }
    */
}
