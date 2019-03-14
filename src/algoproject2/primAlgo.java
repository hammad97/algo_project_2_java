/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoproject2;
import edu.uci.ics.jung.algorithms.layout.BalloonLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.*;
import java.lang.*;
import java.io.*;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;
/**
 *
 * @author Dell
 */
public class primAlgo extends AlgoProject2{
UndirectedSparseGraph g3 ;
double myTotalCount3=0;
//double matrixx[][];
//double AdjencyMatrix3[][];
    primAlgo(String s){
        super(s,0,0);
//        matrixx=new double[9][9];
        g3 = new UndirectedSparseGraph();        
        V=this.numberNodes;
            V=9;
        this.primMST(this.adjencyMatrix);
        this.displayPrim();
    }
// A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
// The program is for adjacency matrix representation of the graph
    // Number of vertices in the graph
    private int V;
 
    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(double key[], Boolean mstSet[])
    {
        // Initialize min value
        double min = Double.MAX_VALUE; 
                int min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int parent[], int n, double graph[][])
    {
//        System.out.println("Edge   Weight");
        for (int i = 0; i < V; i++){
            if(parent[i]>-1){
//            System.out.println(parent[i]+" - "+ i+"    "+
//                               graph[i][parent[i]]);
            String temp="";
            StringBuilder sb=new StringBuilder(temp);
            sb.append('(');
            sb.append(Integer.toString(parent[i]));
            sb.append(',');
            sb.append(Integer.toString(i));
            sb.append(')');
            sb.append(Double.toString(this.adjencyMatrix[parent[i]][i]));
            temp=sb.toString();
            g3.addEdge(temp, parent[i], i);
            myTotalCount3 = myTotalCount3+this.adjencyMatrix[parent[i]][i];
            }
        }
    }
          void displayPrim(){
         System.out.println("/---------------------------------------------------------------/");
         
         System.out.println("Prim:\tTotal Vertices: "+this.numberNodes+"\t Total Cost: "+this.myTotalCount3);         

         System.out.println("/---------------------------------------------------------------/");
         Layout<Integer, String> layout = new KKLayout(g3);
         layout.setSize(new Dimension(850,850));
         BasicVisualizationServer<Integer,String> vv =
         new BasicVisualizationServer<Integer,String>(layout);
         vv.setPreferredSize(new Dimension(900,900));
         
         Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
         public Paint transform(Integer i) {
               if(i!=SOURCEE){
                return Color.CYAN;
             }
             else{
                return Color.YELLOW;
             }
         }
         };
         // Set up a new stroke Transformer for the edges
         float dash[] = {10.0f};
         final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
         BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
         
         Transformer<String, Stroke> edgeStrokeTransformer =
         new Transformer<String, Stroke>() {
         public Stroke transform(String s) {
         return edgeStroke;
         }
         };
         vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
         vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
         vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
         vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
         vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

         JFrame frame = new JFrame("Prim: Minimum Spanning Tree");
         Font f1 = new Font("Verdana", Font.BOLD, 12);
//         frame.setFont(f1);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.getContentPane().add(vv);
         frame.pack();
         frame.setVisible(true);              
         }
    static int edgeCount=0;
        public static class MyNode {
         int id; // good coding practice would have this as private
         public MyNode(int id) {
         this.id = id;
         }
         public String toString() { // Always a good idea for debuging
         return "V"+id; // JUNG2 makes good use of these.
         }
         }

         public static class MyLink {
         double capacity; // should be private
         double weight; // should be private for good practice
         int id;

         public MyLink(double weight, double capacity) {
         this.id = edgeCount++; // This is defined in the outer class.
         this.weight = weight;
         this.capacity = capacity;
         }
         public String toString() { // Always good for debugging
         return "E"+id;
         }

       }
    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    void primMST(double graph[][])
    {
        // Array to store constructed MST
        int parent[] = new int[V];
 
        // Key values used to pick minimum weight edge in cut
        double key[] = new double [V];
 
        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];
 
        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        // Always include first 1st vertex in MST.
        key[1] = 0;     // Make key 0 so that this vertex is
                        // picked as first vertex
        parent[1] = -1; // First node is always root of MST
 
        // The MST will have V vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);
 
            // Add the picked vertex to the MST Set
            mstSet[u] = true;
 
            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)
 
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v]!=0 && mstSet[v] == false &&
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
 
        // print the constructed MST
        printMST(parent, V, graph);
    }

    public static void main (String[] args)
    {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
//        primAlgo t = new primAlgo("D:input10.txt");
//       primAlgo p = new primAlgo("D:input10.txt");
//       
//       p.matrixx= new double [][]{{0,4,0,0,0,0,0,8,0},
//           {0,0,8,0,0,0,0,11,0},
//           {0,0,0,0,7,0,4,0,0,2},
//           {0,0,7,0,9,14,0,0,0},
//           {0,0,0,9,0,10,0,0,0},
//           {0,0,4,14,10,0,2,0,0},
//           {0,0,0,0,0,2,0,1,6},
//           {8,11,0,0,0,0,1,0,7},
//           {0,0,2,0,0,6,0,7,0}};
//       p.primMST(p.matrixx);
//       p.displayPrim();
//        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
//                                    {2, 0, 3, 8, 5},
//                                    {0, 3, 0, 0, 7},
//                                    {6, 8, 0, 0, 9},
//                                    {0, 5, 7, 9, 0},
//                                   };
 
        // Print the solution
//        t.primMST(graph);
    }
}
// This code is contributed by Aakash Hasija    
