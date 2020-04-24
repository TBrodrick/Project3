package com.example.project3;

public class BattleFloor extends  Floors {

    //variables
    private Character[] Enemies = new Character[4];          //an array of all enemies on this floor needs a generate enemy or get enemy
    private Character[] Allies;         //an array of all allies (moved from floor to floor)(get allies?)
    private String [] names = {"Grunt","Shark","Guppy","Zombie Fish","Mimic","Tuna","Beta Fish"};

    private int numEnemies;

    //functions
    //default constructor
    BattleFloor(){
        setFloorNum(0);
        setFloorType(0);

    }

    /**
     *
     * @param n floor number
     * @param allies list of ally characters moving from floor to floor
     */
    BattleFloor(int n,Character[] allies){//n is floor number
        super(0,n);
        if(n%10==0)
            generateBoss(n);
        else
            generateEnemy(n);

        Allies = allies;
    }

    void attackRound()
    {
        for(int i = 0; i<4;i++)         //3 cases
        {
            if(Allies[i].getHealth()>0&&Enemies[i].getHealth()>0)       //both targets are alive
            {
                Enemies[i].takeDamage(Allies[i].getAtkStat() - Enemies[i].getDefStat());
                Allies[i].takeDamage(Enemies[i].getAtkStat() - Allies[i].getDefStat());
            }
            else if(Enemies[i].getHealth()<=0)                         //enemy is dead
            {
                for(int j = 0;j<4;j++)          //search for alternate enemy (make more efficient)
                {
                    if(Enemies[j].getHealth()>0)
                    {
                        Enemies[j].takeDamage(Enemies[j].getDefStat()-Allies[i].getAtkStat());
                        break;
                    }
                }
            }
            else if(Allies[i].getHealth()<=0)                           //ally is dead
            {
                for(int j = 0;j<4;j++)          //search for alternate enemy (make more efficient)
                {
                    if(Allies[j].getHealth()>0)
                    {
                        Allies[j].takeDamage(Allies[j].getDefStat()-Enemies[i].getAtkStat());
                        break;
                    }
                }
            }
            else                                                        //otherwise error
                return;
        }
    }

    void bossAttackRound()
    {
        for(int i = 0;i<4;i++)
        {
            if(Allies[i].getHealth()>0)
                Enemies[0].takeDamage(Enemies[0].getDefStat()-Allies[i].getAtkStat());
            Allies[i].takeDamage(Allies[i].getDefStat()-Enemies[0].getAtkStat());

        }
    }

    //generates enemies based on floor level

    /**
     *
     * @param fn floor number used to determine enemy difficulty
     */
    private void generateEnemy(int fn)
    {
        int rand = (int)(Math.random()*7);
        Character newEnemy = new Character(names[rand],fn,fn);
        Enemies[0] = newEnemy;
        for(int i = 1; i<4;i++)
        {
            rand = (int)(Math.random()*7);
            newEnemy.setName(names[rand]);
            Enemies[i]=newEnemy;
        }
    }

    /**
     *
     * @param index The index of the enemy you wish to retrieve
     * @return The enemy at the given array index
     */
    Character getEnemy(int index){
        return Enemies[index];
    }
    /**
     *
     * @param fn floor number
     */
    private void generateBoss(int fn)
    {
        Enemies[0] = new Enemy(fn*2,fn*2,"BOSS");
    }
}
