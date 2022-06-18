package hw7;

import java.util.ArrayList;

class Vertex{
    public char label;
    public boolean wasVisited;
    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }

    @Override
    public String toString() {
        return "[ " + label + " ]";
    }
}
class Graph{
    private final int MAX_VERTEXES = 32;
    private final Vertex[] vertexList;
    private final int[][] adjMat;
    private int size;

    public Graph(){
        vertexList = new Vertex[MAX_VERTEXES];
        adjMat = new int[MAX_VERTEXES][MAX_VERTEXES];
        size = 0;
        for (int i = 0; i < MAX_VERTEXES; i++) {
            for (int j = 0; j < MAX_VERTEXES; j++) {
                adjMat[i][j] = 0;
            }
        }
    }
    private int getAdjUnvisitedVertex(int ver){
        for (int i = 0; i < size; i++) {
            if(adjMat[ver][i] == 1 && !vertexList[i].wasVisited){
                return i;
            }
        }
        return -1;
    }

    /**
     * Метод поиска кратчайшего пути в графе, на основе обхода в ширину
     * @param beginV стартовая вершина
     * @param finishV конечная вершина
     * @return Список вершин
     */
    public ArrayList<Vertex> findWay(char beginV, char finishV){

        ArrayList<Integer> newWay = new ArrayList<>();//новый путь
        ArrayList<ArrayList<Integer>> ways = new ArrayList<>();//список для хранения "путей"
        ArrayList<Integer> tmpWay;//текущий путь
        Queue queue = new Queue();//Очередь для обхода в ширину
        int v2, v1; //вершины в обходе

        int begin = -1;//номер начальной вершины
        int finish = -1;//номер конечной вершины

        for (int i = 0; i < size; i++) {//находим номера вершин
            if (vertexList[i].label == beginV)
                begin = i;
            if (vertexList[i].label == finishV)
                finish = i;
        }

        if (begin < 0 || finish < 0) //если не нашли вершины
            throw new RuntimeException("No such a Vertex");

        vertexList[begin].wasVisited = true;//начало пути
        newWay.add(begin);
        ways.add(newWay);
        queue.add(begin); // Вставка в конец очереди

        while(!queue.isEmpty()){
            v1 = queue.remove();
            while((v2=getAdjUnvisitedVertex(v1)) != -1){

                for (int i = 0; i < ways.size(); i++) {//находим "предыдущую" вершину и делаем новый маршрут
                    tmpWay = ways.get(i);
                    if (tmpWay.get(tmpWay.size()-1).equals(v1)) {
                        newWay = (ArrayList<Integer>)tmpWay.clone();
                        newWay.add(v2);
                        ways.add(newWay);
                        break;
                    }
                }
                if (v2 == finish){//если пришли к финишу
                    clearFlags();
                    ArrayList<Vertex> outVertexes = new ArrayList<>();
                    for (Integer i: newWay)
                        outVertexes.add(vertexList[i]);
                    return outVertexes;
                }

                vertexList[v2].wasVisited = true; // Пометка
                queue.add(v2);
            }
        }
        clearFlags();
        return null;
    }

    private void clearFlags() {
        for(int i=0; i<size; i++) // Сброс флагов
            vertexList[i].wasVisited = false;
    }

    public void addVertex(char label){
        vertexList[size++] = new Vertex(label);
    }
    public void addEdge(int start, int end){
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }
}
public class Main{
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');//0
        graph.addVertex('B');//1
        graph.addVertex('C');//2
        graph.addVertex('D');//3
        graph.addVertex('E');//4
        graph.addVertex('F');//5
        graph.addVertex('G');//6
        graph.addVertex('H');//7
        graph.addVertex('I');//8
        graph.addVertex('J');//9
        graph.addVertex('K');//10

        graph.addEdge(0, 2); //AC
        graph.addEdge(2, 5); //CF
        graph.addEdge(2, 3); //CD
        graph.addEdge(1, 3); //BD
        graph.addEdge(1, 4); //BE
        graph.addEdge(3, 4); //DE
        graph.addEdge(3, 6); //DG
        graph.addEdge(4, 8); //EI
        graph.addEdge(5, 9); //FJ
        graph.addEdge(6, 7); //GH
        graph.addEdge(6, 9); //GJ
        graph.addEdge(8, 10); //IK
        graph.addEdge(9, 10); //JK

        System.out.println(graph.findWay('A','K'));
    }
}

