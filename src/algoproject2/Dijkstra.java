/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoproject2;

/**
 *
 * @author Dell
 */
// The program is for adjacency matrix representation of the graph
import edu.uci.ics.jung.algorithms.layout.BalloonLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
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
import javax.swing.JOptionPane;
import org.apache.commons.collections15.Transformer;
public class Dijkstra extends AlgoProject2{
DirectedSparseGraph g2 ;
double AdjencyMatrix2[][];
// A Java program for Dijkstra's single source shortest path algorithm.
 

    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet leincluded in shortest path tree
//    static final int V=9;
    String wholePath="";
    StringBuilder sb;
    double myTotalCount2=0;
    Dijkstra(String s){
        super(s,0,0);
        g2 = new DirectedSparseGraph();
        sb=new StringBuilder(wholePath);
        AdjencyMatrix2 = new double[this.numberNodes][this.numberNodes];
        for(int i=0;i<this.numberNodes;i++){
            g2.addVertex(i);
        }
        if(this.NEGATIVEE==false){
            dijkstra(this.adjencyMatrix, SOURCEE);
            displayGraphDijkstra();   
        }
        else{
            JOptionPane.showMessageDialog(null, "Graph cant be plot it contain negative edge(s)");            
        }
    }
    int minDistance(double dist[], Boolean sptSet[])
    {
        // Initialize min value
        double min = Double.MAX_VALUE;int min_index=-1;
 
        for (int v = 0; v < numberNodes; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
          void displayGraphDijkstra(){
//         g2.addEdge("", 1, 2);
//        int last=0;
//        int strSplit=0;
//        int source=0,destination=0;
//         for(int i=0;i<this.wholePath.length();i++){
//             if(wholePath.charAt(i)==',' && strSplit==0 ){
//                 String temp=wholePath.substring(last, i);
//                 last=i+1;
//                 source=Integer.parseInt(temp);
//                 strSplit=1;
//             }
//         else if(wholePath.charAt(i)==',' && strSplit==1 ){
//                String temp=wholePath.substring(last, i);
//                 last=i+1;
//                 destination=Integer.parseInt(temp);
//                 strSplit=0;
//                 
//                String temp2="";
//                StringBuilder sb2=new StringBuilder(temp2);
//                sb2.append('(');
//                sb2.append(Integer.toString(source));
//                sb2.append(',');
//                sb2.append(Integer.toString(destination));
//                sb2.append(')');
//                sb2.append(Double.toString(this.adjencyMatrix[source][destination]));
//                temp2=sb2.toString();
//                g2.addEdge(temp2, source, destination);              
//            }
//         }
//int i2=0,j2=0;
//                 double min=50;
//         for(int j=0;j<this.numberNodes;j++){
//             min=50;
//             i2=0;j2=0;
//             for(int i=0;i<this.numberNodes;i++){
//                 if(AdjencyMatrix2[i][j]<min)
//                 {
//                     min=AdjencyMatrix2[i][j];
//                 }
//                 }
//             for(int i=0;i<this.numberNodes;i++){
//                 if(this.AdjencyMatrix2[i][j]!=min){
//                     AdjencyMatrix2[i][j]=0;
//                 }
//             }
//         }
         
        double min=50;int minI=0;
         for(int j=0;j<this.numberNodes;j++){
             min=50;
             minI=0;
             for(int i=0;i<this.numberNodes;i++){
                 if(this.AdjencyMatrix2[i][j]<min && this.AdjencyMatrix2[i][j]!=0){
                     min=this.AdjencyMatrix2[i][j];
                     minI=i;
                 }
             }
             for(int k=0;k<this.numberNodes;k++){
                 if(k!=minI){
                     this.AdjencyMatrix2[k][j]=0;
                 }
             }
             
         }

         for(int i=0;i<this.numberNodes;i++){
             for(int j=0;j<this.numberNodes;j++){
                 if(AdjencyMatrix2[i][j]!=0)
                 {
                    String temp2="";
                StringBuilder sb2=new StringBuilder(temp2);
                sb2.append('(');
                sb2.append(Integer.toString(i));
                sb2.append(',');
                sb2.append(Integer.toString(j));
                sb2.append(')');
                sb2.append(Double.toString(this.adjencyMatrix[i][j]));
                temp2=sb2.toString();
                g2.addEdge(temp2, i, j);    
                 myTotalCount2=myTotalCount2+this.adjencyMatrix[i][j];
                 
                 }
             
                 
             }
         }         
         
         System.out.println("/---------------------------------------------------------------/");
         
         System.out.println("Dijkstra:\tTotal Vertices: "+this.numberNodes+"\t Total Cost: "+this.myTotalCount2);         

         System.out.println("/---------------------------------------------------------------/");             
         Layout<Integer, String> layout = new FRLayout(g2);
         layout.setSize(new Dimension(800,800));
         BasicVisualizationServer<Integer,String> vv =
         new BasicVisualizationServer<Integer,String>(layout);
         vv.setPreferredSize(new Dimension(850,850));
         
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

         JFrame frame = new JFrame("Dijkstra Graph");
         Font f1 = new Font("Verdana", Font.BOLD, 12);
//         frame.setFont(f1);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.getContentPane().add(vv);
         frame.pack();
         frame.setVisible(true);              
         }
    // A utility function to print the constructed distance array
    void printSolution(double dist[], int n)
    {
//        System.out.println("Vertex   Distance from Source");
//        for (int i = 0; i < numberNodes; i++)
//            System.out.println(i+" \t\t "+dist[i]);
    }
 
    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(double graph[][], int src)
    {
        double dist[] = new double[this.numberNodes]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        
        Boolean sptSet[] = new Boolean[numberNodes];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < numberNodes; i++)
        {
            dist[i] = Double.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < numberNodes-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < numberNodes; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v]){
                    
                    dist[v] = dist[u] + graph[u][v];
                     this.AdjencyMatrix2[u][v]=this.adjencyMatrix[u][v];


//                    sb.append(Integer.toString(u));
//                    sb.append(',');
//                    sb.append(Integer.toString(v));
//                    sb.append(',');
                }
        }
 
        // print the constructed distance array
        printSolution(dist, numberNodes);
    }
 
    // Driver method
    public static void main (String[] args)
    {
        
        Dijkstra t = new Dijkstra("D:input10.txt");

    }
}
