package entities;

import Graphics.Tiles.Tile;
import entities.staticEntities.MoonRock;
import entities.staticEntities.Tree;
import entities.staticEntities.TreeLog;
import main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private ArrayList<ArrayList<Entity>> entitiesKeeper;
    private boolean clearedEntities = false;
    private Entity _testEntity;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b){
            if(a.getY()+a.getHeight() < b.getY()+b.getHeight()){
                return -1;
            }else{
                return 1;
            }
        }
    };
    public EntityManager(Handler handler){
        setHandler(handler);
        entities = new ArrayList<Entity>();
        entitiesKeeper = new ArrayList<ArrayList<Entity>>();

    }

    public void clearEntities(){
        storeEntities(entities);
        removeAllEntities();
        clearedEntities = true;
    }
    public void removeAllEntities(){
        for(Entity e : entities){
            if(!(e instanceof Player)){
                e.setActive(false);
            }
        }
    }
    public void addPlayer(Player player){
        setPlayer(player);
        addEntity(player);
    }

    public void tick(){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if(!e.isActive()){
                it.remove();
            }
        }
        if(clearedEntities){
            entities = new ArrayList<Entity>();
            entities.add(player);
            clearedEntities = false;
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics2D g2d){
        for (Entity e : entities){
            e.render(g2d);
        }
        player.postRender(g2d);
    }

    public void addEntity(Entity e){
        entities.add(e);
    }
    private int getIdOfPlayerInEntities(){
        int i = 0;
        for(Entity e: entities){
            if(e instanceof Player){
                return i;
            }
            i++;
        }
        return -1;
    }
    //getter and setters


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public ArrayList<Entity> getEntitiesStore(int id){
        return entitiesKeeper.get(id);
    }

    private void storeEntities(ArrayList<Entity> entitiesArrayList){
        ArrayList<Entity> _entitiesArrayList = (ArrayList<Entity>)entitiesArrayList.clone();
        _entitiesArrayList.remove(player);
        entitiesKeeper.add(_entitiesArrayList);
    }
    public void addPlayer(){
        entities.add(player);
    }

    public Entity getEntityClass() {
        return  _testEntity = new MoonRock(handler,0,0,0,0);
    }
}
