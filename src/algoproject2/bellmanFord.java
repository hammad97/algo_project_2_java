/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoproject2;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
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
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author Dell
 */
public class bellmanFord extends AlgoProject2{
    public int myParent[];
    public double myWeight[];
    private double distances[];
    private int numberofvertices;
    public double adjencyMatrix5[][];
    public DirectedSparseGraph g5 ;
    double myTotalCount5=0;
//    public static final int MAX_VALUE = 999;
 
    public bellmanFord(String s)
    {
        super(s,0,0);
        g5 = new DirectedSparseGraph();                
        this.numberofvertices = this.numberNodes;
        distances = new double[numberofvertices];
        adjencyMatrix5=new double [numberofvertices][numberofvertices];
        myParent= new int[this.numberofvertices];
        myWeight= new double[this.numberofvertices];
        for (int sourcenode = 0; sourcenode < numberofvertices; sourcenode++)
        {
            for (int destinationnode = 0; destinationnode < numberofvertices; destinationnode++)
            {
 	        if (sourcenode == destinationnode)
                {
                    adjencyMatrix[sourcenode][destinationnode] = 0;
                    continue;
                }
                if (adjencyMatrix[sourcenode][destinationnode] == 0)
                {
                    adjencyMatrix[sourcenode][destinationnode] = Double.MAX_VALUE;
                }
            }
        }
        BellmanFordEvaluation(SOURCEE, this.adjencyMatrix);
        this.displayBellmanFord();
    }
 
    public void BellmanFordEvaluation(int source, double adjacency[][])
    {
        for (int node = 0; node < numberofvertices; node++)
        {
            distances[node] = Double.MAX_VALUE;
        }
 
        distances[source] = 0;
        for (int node = 0; node < numberofvertices; node++)
        {
            for (int sourcenode = 0; sourcenode < numberofvertices; sourcenode++)
            {
                for (int destinationnode = 0; destinationnode < numberofvertices; destinationnode++)
                {
                    if (adjencyMatrix[sourcenode][destinationnode] != Double.MAX_VALUE)
                    {
                        if (distances[destinationnode] > distances[sourcenode] 
                                + adjencyMatrix[sourcenode][destinationnode]){
                            distances[destinationnode] = distances[sourcenode]
                                + adjencyMatrix[sourcenode][destinationnode];
                            
                            myParent[destinationnode]=sourcenode;
                            myWeight[destinationnode]=this.adjencyMatrix[sourcenode][destinationnode];
                           
                            adjencyMatrix5[sourcenode][destinationnode]=adjencyMatrix[sourcenode][destinationnode];
                        }
                    }
                }   
            }
        }
 
        for (int sourcenode = 0; sourcenode < numberofvertices; sourcenode++)
        {
            for (int destinationnode = 0; destinationnode < numberofvertices; destinationnode++)
            {
                if (adjencyMatrix[sourcenode][destinationnode] != Double.MAX_VALUE)
                {
                    if (distances[destinationnode] > distances[sourcenode]
                           + adjencyMatrix[sourcenode][destinationnode])
                        System.out.println("The Graph contains negative egde cycle");
                }
            }
        }
 
//        for (int vertex = 0; vertex < numberofvertices; vertex++)
//        {
//            System.out.println("distance of source  " + source + " to "
//                      + vertex + " is " + distances[vertex]);
//        }
    }
    
          void displayBellmanFord(){
         
//        double min=50;int minI=0;
//         for(int j=0;j<this.numberNodes;j++){
//             min=50;
//             minI=0;
//             for(int i=0;i<this.numberNodes;i++){
//                 if(this.adjencyMatrix5[i][j]<min && this.adjencyMatrix5[i][j]!=0){
//                     min=this.adjencyMatrix5[i][j];
//                     minI=i;
//                 }
//             }
//             for(int k=0;k<this.numberNodes;k++){
//                 if(k!=minI){
//                     this.adjencyMatrix5[k][j]=0;
//                 }
//             }
//             
//         }

//         for(int i=0;i<this.numberNodes;i++){
//             for(int j=0;j<this.numberNodes;j++){
//                 if(this.adjencyMatrix5[i][j]!=0)
//                 {
//                    String temp2="";
//                StringBuilder sb2=new StringBuilder(temp2);
//                sb2.append('(');
//                sb2.append(Integer.toString(i));
//                sb2.append(',');
//                sb2.append(Integer.toString(j));
//                sb2.append(')');
//                sb2.append(Double.toString(this.adjencyMatrix[i][j]));
//                temp2=sb2.toString();
//                g5.addEdge(temp2, i, j);    
//                 
//                 
//                 }
//             
//                 
//             }
//         }         
         for(int i=0;i<this.numberofvertices;i++){
                    String temp2="";
                StringBuilder sb2=new StringBuilder(temp2);
                sb2.append('(');
                sb2.append(Integer.toString(myParent[i]));
                sb2.append(',');
                sb2.append(Integer.toString(i));
                sb2.append(')');
                sb2.append(Double.toString(myWeight[i]));
                temp2=sb2.toString();
                if(myWeight[i]!=0)
                {
                    g5.addEdge(temp2, myParent[i], i);
                    this.myTotalCount5=this.myTotalCount5+myWeight[i];
                }             
                
         }
         System.out.println("/---------------------------------------------------------------/");
         
         System.out.println("Bellmen:\tTotal Vertices: "+this.numberNodes+"\t Total Cost: "+this.myTotalCount5);         

         System.out.println("/---------------------------------------------------------------/");             
         Layout<Integer, String> layout = new FRLayout(g5);
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

         JFrame frame = new JFrame("Bellmen Ford Graph");
         Font f1 = new Font("Verdana", Font.BOLD, 12);
//         frame.setFont(f1);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.getContentPane().add(vv);
         frame.pack();
         frame.setVisible(true);              
         }    
 
    public static void main(String... arg)
    {
//        int numberofvertices = 0;
//        int source;
//        Scanner scanner = new Scanner(System.in);
// 
//        System.out.println("Enter the number of vertices");
//        numberofvertices = scanner.nextInt();
// 
//        int adjencyMatrix[][] = new int[numberofvertices + 1][numberofvertices + 1];
//        System.out.println("Enter the adjacency matrix");
//        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
//        {
//            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
//            {
//                adjencyMatrix[sourcenode][destinationnode] = scanner.nextInt();
// 	        if (sourcenode == destinationnode)
//                {
//                    adjencyMatrix[sourcenode][destinationnode] = 0;
//                    continue;
//                }
//                if (adjencyMatrix[sourcenode][destinationnode] == 0)
//                {
//                    adjencyMatrix[sourcenode][destinationnode] = MAX_VALUE;
//                }
//            }
//        }
// 
//        System.out.println("Enter the source vertex");
//        source = scanner.nextInt();
// 
        bellmanFord obj = new bellmanFord("D:input10.txt");

//        scanner.close();	
    }    
}
