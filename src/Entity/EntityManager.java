package Entity;

import BaseMainGame.GameHandler;
import Entity.Creature.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class EntityManager {   //is used in World_2D_Tile

    private ArrayList<Entity> m_Entities;
    private Player m_Player;

    public EntityManager(Player _Player)
    {
        m_Entities = new ArrayList<Entity>();
        this.m_Player = _Player;

        //after create new Player above --> the Player has to had gameobject --> or NULL
        //because I designed this shit


        this.m_Player.SetGameObject(GameHandler.GetInstance().get_m_gameObject());

        m_Entities.add(m_Player);

        GameHandler.GetInstance().set_m_Player(m_Player);
    }

    public void Update()
    {
        for(int i = 0 ; i < m_Entities.size();)
        {
            Entity currentEntity = m_Entities.get(i);

            currentEntity.Update();

            if(!currentEntity.is_Active())
            {
                m_Entities.remove(currentEntity);
                continue;  //once we remove --> the index should be the same --> avoid i++
            }

            i++;
        }

        m_Entities.sort(m_EntitiesComparator);
    }

    public void Render(Graphics m_RealScreenObject)
    {
        for(Entity currentEntity : m_Entities)
        {
            currentEntity.Render(m_RealScreenObject);
        }

        m_Player.RenderInventoryLast(m_RealScreenObject);
    }

    public void addEntity(Entity _new_entity)
    {
        m_Entities.add(_new_entity);
    }

    public ArrayList<Entity> get_m_Entities()
    {
        return m_Entities;
    }


    //for Entities sorting
    private Comparator<Entity> m_EntitiesComparator = new Comparator<Entity>() {
        @Override
        public int compare(Entity o1, Entity o2) {
            if((o1.getY() + o1.getEntityHeight()) < (o2.getY() + o2.getEntityHeight()))
                return -1;
            return 1;
        }
    };
}
