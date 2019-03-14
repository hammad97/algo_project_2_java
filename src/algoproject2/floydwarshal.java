/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoproject2;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.DefaultVisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
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
public class floydwarshal extends AlgoProject2{
    public int myParent1[];
    public double myWeight1[];
    public int myParent2[];
    public double myWeight2[];
    private double distancematrix[][];
    private int numberofvertices;
    public static final int INFINITY = 999;
     public double adjencyMatrix6[][];
    public UndirectedSparseGraph g6 ;
    double myTotalCount6=0;
    public floydwarshal(String s)
    {
        super(s,0,1);
        g6 = new UndirectedSparseGraph();
        distancematrix = new double[this.numberNodes][this.numberNodes];
        this.numberofvertices = this.numberNodes;
        myParent1= new int[this.numberofvertices];
        myWeight1= new double[this.numberofvertices];        
        myParent2= new int[this.numberofvertices];
        myWeight2= new double[this.numberofvertices];
        adjencyMatrix6=new double [numberofvertices][numberofvertices];
        for (int source = 0; source < numberofvertices; source++)
        {
            for (int destination = 0; destination < numberofvertices; destination++)
            {
                if (source == destination)
                {
                    adjencyMatrix[source][destination] = 0;
                    continue;
                }
                if (adjencyMatrix[source][destination] == 0)
                {
                    adjencyMatrix[source][destination] = INFINITY;
                }
            }
        }
 
//        System.out.println("The Transitive Closure of the Graph");
        floydwarshall(adjencyMatrix);
        this.displayFloyd();
    }
 
    public void floydwarshall(double adjacencymatrix[][])
    {
        for (int source = 0; source < numberofvertices; source++)
        {
            for (int destination = 0; destination < numberofvertices; destination++)
            {
                distancematrix[source][destination] = adjacencymatrix[source][destination];
            }
        }
 
        for (int intermediate = 0; intermediate < numberofvertices; intermediate++)
        {
            for (int source = 0; source < numberofvertices; source++)
            {
                for (int destination = 0; destination < numberofvertices; destination++)
                {
                    if (distancematrix[source][intermediate] + distancematrix[intermediate][destination]
                         < distancematrix[source][destination]){
                        distancematrix[source][destination] = distancematrix[source][intermediate] 
                            + distancematrix[intermediate][destination];
                    
                        
                        myParent1[destination]=intermediate;
                        myWeight1[destination]=this.adjencyMatrix[intermediate][destination];
                        
                        myParent2[intermediate]=source;
                        myWeight2[intermediate]=this.adjencyMatrix[source][intermediate];                        
                                
                        adjencyMatrix6[source][intermediate]=this.adjencyMatrix[source][intermediate];
                        adjencyMatrix6[intermediate][destination]=this.adjencyMatrix[intermediate][destination];
                    }
                }
            }
        }
 
//        for (int source = 0; source < numberofvertices; source++)
//            System.out.print("\t" + source);
// 
//        System.out.println();
//        for (int source = 0; source < numberofvertices; source++)
//        {
////            System.out.print(source + "\t");
//            for (int destination = 0; destination < numberofvertices; destination++)
//            {
////                System.out.print(distancematrix[source][destination] + "\t");
//            }
//            System.out.println();
//        }
    }
           void displayFloyd(){
         
//        double min=50;int minI=0;
//         for(int j=0;j<this.numberNodes;j++){
//             min=50;
//             minI=0;
//             for(int i=0;i<this.numberNodes;i++){
//                 if(this.adjencyMatrix6[i][j]<min && this.adjencyMatrix6[i][j]!=0){
//                     min=this.adjencyMatrix6[i][j];
//                     minI=i;
//                 }
//             }
//             for(int k=0;k<this.numberNodes;k++){
//                 if(k!=minI){
//                     this.adjencyMatrix6[k][j]=0;
//                 }
//             }
//             
//         }

         for(int i=0;i<this.numberNodes;i++){
             for(int j=0;j<this.numberNodes;j++){
                 if(this.adjencyMatrix6[i][j]!=0 && this.adjencyMatrix6[i][j]!=INFINITY)
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
                g6.addEdge(temp2, i, j); 
                this.myTotalCount6=this.myTotalCount6+this.adjencyMatrix[i][j];                
                 
                 }
             
                 
             }
         }         
//         for(int i=0;i<this.numberNodes;i++){
//                 if(this.myWeight1[i]!=0.0 && this.myWeight1[i]!=INFINITY)
//                 {
//                    String temp2="";
//                StringBuilder sb2=new StringBuilder(temp2);
//                sb2.append('(');
//                sb2.append(Integer.toString(myParent1[i]));
//                sb2.append(',');
//                sb2.append(Integer.toString(i));
//                sb2.append(')');
//                sb2.append(Double.toString(myWeight1[i]));
//                temp2=sb2.toString();
//                g6.addEdge(temp2, myParent1[i], i); 
//                this.myTotalCount6=this.myTotalCount6+myWeight1[i];
//                 }
//                if(this.myWeight2[i]!=0.0 && this.myWeight1[i]!=INFINITY)
//                 {
//                    String temp2="";
//                StringBuilder sb2=new StringBuilder(temp2);
//                sb2.append('(');
//                sb2.append(Integer.toString(myParent2[i]));
//                sb2.append(',');
//                sb2.append(Integer.toString(i));
//                sb2.append(')');
//                sb2.append(Double.toString(myWeight2[i]));
//                temp2=sb2.toString();
//                g6.addEdge(temp2, myParent2[i], i); 
//                this.myTotalCount6=this.myTotalCount6+myWeight2[i];                
//                
//                 }                 
//         }         
         System.out.println("/---------------------------------------------------------------/");
         
         System.out.println("Floyd:\tTotal Vertices: "+this.numberNodes+"\t Total Cost: "+this.myTotalCount6);         

         System.out.println("/---------------------------------------------------------------/");
         Layout<Integer, String> layout = new CircleLayout(g6);
         layout.setSize(new Dimension(750,750));
//         BasicVisualizationServer<Integer,String> vv =
//         new BasicVisualizationServer<Integer,String>(layout);
         VisualizationModel visualizationModel = new DefaultVisualizationModel(layout);
         VisualizationViewer<String, String> vv = new VisualizationViewer(visualizationModel); 
         vv.setPreferredSize(new Dimension(850,850));
         
         Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
         public Paint transform(Integer i) {
//            if(i!=SOURCEE){
                return Color.CYAN;
//             }
//             else{
//                return Color.YELLOW;
//             }
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
         DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();
         graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
         vv.setGraphMouse(graphMouse);      
         
//         vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
         vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
         vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
         vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
         vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

         JFrame frame = new JFrame("Floyd Warshal Graph");
         Font f1 = new Font("Verdana", Font.BOLD, 12);
//         frame.setFont(f1);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.getContentPane().add(vv);
         frame.pack();
         frame.setVisible(true);              
         } 
    public static void main(String... arg)
    {
//        int adjacency_matrix[][];
//        int numberofvertices;
//        System.out.println("Enter the number of vertices");
//        numberofvertices = scan.nextInt();
// 
//        adjacency_matrix = new int[numberofvertices + 1][numberofvertices + 1];
//        System.out.println("Enter the Weighted Matrix for the graph");
        floydwarshal obj=new floydwarshal("D:input20.txt");


    }
    
}
