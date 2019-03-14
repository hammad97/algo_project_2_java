/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoproject2;

//import edu.uci.ics.jung.algorithms.layout.CircleLayout;
//import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.*;
//import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.*;
//import edu.uci.ics.jung.graph.util.EdgeType;
//import java.awt.Dimension;
import javax.swing.JFrame;
//import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.DefaultVisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;
//import edu.uci.ics.jung.visualization.VisualizationImageServer;
//import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import org.apache.commons.collections15.Transformer;
//import org.apache.commons.collections15.functors.ConstantTransformer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JLabel;
import edu.uci.ics.jung.visualization.control.*;


/**
 *
 * @author Dell
 */
public class AlgoProject2 {
DirectedSparseGraph g ;
double myTotalCount=0;
int numberNodes=0;
double adjencyMatrix[][];
String fPath;
int SOURCEE;
boolean NEGATIVEE=false;
    AlgoProject2(String s,int draw, int v){
        fPath=s;    
        g = new DirectedSparseGraph();
/////////////////////////////////////////////////////////////////////////////////
        File f = new File(fPath);
        if(f.exists()){
            int count=0;
            int k=0;
            int c=0;
            int xVal=0,yVal=0;
            String value="";
            int numberOfNodes=0;

            String sCurrentLine,x="",y="";
            try (BufferedReader br = new BufferedReader(new FileReader(fPath))) {

                while ((sCurrentLine = br.readLine()) != null) {
                    count++;
                    if(count==3){
                         numberOfNodes=Integer.parseInt(sCurrentLine);
                         numberNodes=numberOfNodes;
                         adjencyMatrix= new double[numberOfNodes][numberOfNodes];                         
//                        System.out.println(numberOfNodes);
                    }
                    else if(numberOfNodes!=0 && count>4){
                        k++;
                        if(k<=numberOfNodes){
                            int strSplit=0;int last=0;
                            for(int i=0;i<sCurrentLine.length();i++){
                                if(sCurrentLine.charAt(i)=='\t'){
                                    if(strSplit==0){
                                        strSplit++;
                                        String temp=sCurrentLine.substring(last, i);
                                        value=temp;
                                        last=i+1;   //14   14241412    141414
                                                     //5   12434343     7674            
                                        g.addVertex(Integer.parseInt(temp)); 
                                    }
                            }
                        }
                        }
                        else if(k>(numberOfNodes+1) && c<=numberOfNodes){
                            c++;
                            int source=0,destination=0;
                            int strSplit=0;int last=0;
                            double weight=0;
                            for(int i=0;i<sCurrentLine.length();i++){
                                if(sCurrentLine.charAt(i)=='\t'){
                                    if(strSplit==0){
                                        strSplit++;
                                        String temp=sCurrentLine.substring(last, i);
                                        last=i+1;   //14   14241412    141414
                                                     //5   12434343     7674            
                                        source=(Integer.parseInt(temp));
//                                        System.out.println("If strsplit==0 { "+"Source: "+source+"Destination= "+destination);
                                    }
                                }
                            }
                                        for(int j=last;j<sCurrentLine.length();j++){
                                            if(sCurrentLine.charAt(j)=='\t' && strSplit==1){
                                                strSplit++;
                                                String temp=sCurrentLine.substring(last, j);
                                                last=j+1;   //14   14241412    141414
                                                             //5   12434343     7674            
                                                destination=(Integer.parseInt(temp));
//                                                System.out.println("If strsplit==1 { "+"Source: "+source+"Destination= "+destination);                                                
                                            }
                                            else if(sCurrentLine.charAt(j)=='\t' && strSplit==2){
                                                //ignore me 
                                                strSplit++;
                                                last=j+1;
//                                                System.out.println("If strsplit==2 { "+"Source: "+source+"Destination= "+destination);                                                
                                            }
                                            else if(sCurrentLine.charAt(j)=='\t' && strSplit==3){
                                                strSplit++;
                                                String temp=sCurrentLine.substring(last, j);
                                                last=j+1;   //14   14241412    141414
                                                             //5   12434343     7674            
                                                weight=(Double.parseDouble(temp));
                                                weight=weight/10000000;
//                                                System.out.println("If strsplit==3 { "+"Source: "+source+"Destination= "+destination);                                                
                                            }
                                            else if(sCurrentLine.charAt(j)=='\t' && strSplit==4){
                                                strSplit=1;
//                                                System.out.println("If Strsplit==4 addedge{ "+"Source: "+source+"Destination= "+destination);
                                                String temp="";
                                                StringBuilder sb=new StringBuilder(temp);
                                                sb.append('(');
                                                sb.append(Integer.toString(source));
                                                sb.append(',');
                                                sb.append(Integer.toString(destination));
                                                sb.append(')');
                                                sb.append(Double.toString(weight));
                                                temp=sb.toString();
                                                g.addEdge(temp, source, destination);
                                                last=j+1;
                                                this.adjencyMatrix[source][destination]=weight;
                                                myTotalCount=myTotalCount+weight;
                                                if(weight<0){
                                                    this.NEGATIVEE=true;
                                                }
                                            }
                                        }
                                    
                            
                        }else{
                            if(c==numberOfNodes+2)
                                SOURCEE=Integer.parseInt(sCurrentLine);
                            c++;
                        }
                    }
                }
                if(v==0)
                    this.finaladjency();
            }catch (Exception e) {
                e.printStackTrace();
            }
            if(draw==1)
                this.displayGraph();
        }
////////////////////////////filing)        
//        g.addVertex((Integer)1);
//        g.addVertex((Integer)2);
//        g.addVertex((Integer)3); 

//        g.addEdge(Integer.toString(20), 2, 3);  
        

         }
    void finaladjency(){
         for(int i=0;i<numberNodes;i++){
            for(int j=0;j<numberNodes;j++){
               if(this.adjencyMatrix[i][j]==0)
               {
                   if(this.adjencyMatrix[j][i]!=0){
                       this.adjencyMatrix[i][j]=this.adjencyMatrix[j][i];                       
                       String temp="";
                        StringBuilder sb=new StringBuilder(temp);
                        sb.append('(');
                        sb.append(Integer.toString(i));
                        sb.append(',');
                        sb.append(Integer.toString(j));
                        sb.append(')');
                        sb.append(Double.toString(this.adjencyMatrix[j][i]));
                        temp=sb.toString();
                        g.addEdge(temp, i, j);
                   }



               }
            }
        }
        
    
    }
    
         void displayGraph(){
         System.out.println("/---------------------------------------------------------------/");
         
         System.out.println("Actual Graph:\tTotal Vertices: "+this.numberNodes+"\t Total Cost: "+this.myTotalCount);         

         System.out.println("/---------------------------------------------------------------/");
         Layout<Integer, String> layout = new CircleLayout(g);
         layout.setSize(new Dimension(750,750));
         VisualizationModel visualizationModel = new DefaultVisualizationModel(layout);
         VisualizationViewer<String, String> vv = new VisualizationViewer(visualizationModel);
//         BasicVisualizationServer<Integer,String> vv =
//         new BasicVisualizationServer<Integer,String>(layout);
         vv.setPreferredSize(new Dimension(1050,1050));
         
         Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
         public Paint transform(Integer i) {
         return Color.CYAN;
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
//        vv.getRenderContext().setVertexFillPaintTransformer(t);
         vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
         vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
         vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
         vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

         JFrame frame = new JFrame("Current Graph");
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
//}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlgoProject2 obj= new AlgoProject2("D:input20.txt",1,0);
//        obj.displayGraph();
        for(int i=0;i<obj.numberNodes;i++){
            for(int j=0;j<obj.numberNodes;j++){
                System.out.print(obj.adjencyMatrix[i][j]+"\t");
            }
            System.out.println();
        }
            
    };

}
     
