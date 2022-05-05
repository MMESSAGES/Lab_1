package P3;
import java.util.*;

public class FriendshipGraph {
    private final Map<Person, List<Person>> map = new HashMap<>();
    private final List<Person> persons = new ArrayList<>();
    public static int flag = 0;
    protected void addVertex (Person vertex) {
        flag = 0;
        if(persons.contains(vertex)){
            System.out.println("Each person has a unique name!");
            return;
        }persons.add(vertex);
        List<Person> friends = new ArrayList<>();
        map.put(vertex, friends);

        flag = 1;
    }
    protected void addEdge(Person n1, Person n2){
        flag = 0;
        Person p;
        if(!persons.contains(n1) || !persons.contains(n2)){
            int size = map.size();
            Set<Person> keys = map.keySet();   //此行可省略，直接将map.keySet()写在for-each循环的条件中
            for(Person key:keys){
                System.out.println("key值："+key.getName(key));
            }System.out.println(n1.getName(n1));
            System.out.println(n2.getName(n2));
            System.out.println("The person is not in the map!");
            return;
        }//判读是否为合法点
        int n = map.get(n1).size();
        int i = 0;
        boolean sign = true;
        while(i < n){
            if(n2 == map.get(n1).get(i)){
                sign = false;
                break;
            }i++;
        }
        if(sign) {
            map.get(n1).add(n2);
            flag = 1;
        }
    }
    protected int getDistance(Person n1, Person n2){
        flag = 0;
        if(!map.containsKey(n2) || !map.containsKey(n1)){
            System.out.println("The person is not in the map!");
            return -1;
        }//判读是否为合法点
        if(n1 == n2){
            System.out.println("They are the same person.");
            return 0;
        }//同一个人直接出
        Queue<Person> vsQueue = new LinkedList<>();
        vsQueue.offer(n1);
        Person np;
        int f_length;
        int height = 0;
        List<Person> sought = new ArrayList<>();
        sought.add(n1);
        //深度优先用队列
        while(!vsQueue.isEmpty()){
            np = vsQueue.poll();
            f_length = map.get(np).size();
            height++;
            for(int i=0; i < f_length; i++){
                if(n2 == map.get(np).get(i)){
                    return height;
                }
                if(!sought.contains(map.get(np).get(i))){
                    sought.add(map.get(np).get(i));
                    vsQueue.offer(map.get(np).get(i));
                }//视情况入队列
            }
        }
        System.out.println("They have no relationship.");

        flag = 1;
        return -1;
    }
}
