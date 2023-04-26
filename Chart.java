/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;

/**
 *
 * @author habib
 */
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Chart extends Rectangle {

    private double x  , y , width , height ; 
    
    private Pane pane ; 
    private ArrayList<Double> positions ;
    
   
    public Chart(double x , double y , double width  , double height, Pane pane) {
        super(x,y,width,height);
        this.x = x ;
        this.y =y ; 
        this.width = width ; 
        this.height =height ; 
        this.pane  = pane ;
        super.setFill(new Color(.03, .43 , .12 ,.22));
        positions =new ArrayList<Double>();
        
    }
    

  
    
    public void divde(ArrayList<Integer> paritions,ArrayList<Integer>names,int start){
        int sum = 0 ; 
        ArrayList<String> label = convertIT(names);
        for(int i = 0; i<paritions.size() ;++i){
            sum += paritions.get(i) ;
        }
    double  split = width / sum ;
        
      
        Text t0 = new Text(x , y+height +20 , start+""); 
        t0.setStyle("-fx-font-size:15px");
     
        int last = 0; 
       if(paritions.size()!=1){
        for(int i = 0 ; i<paritions.size()-1 ; ++i){
           double x = (this.x+(split*( paritions.get(i)+last)))  ;
       
         
         positions.add(x);
            Line l= new Line(x,y,x,y+height);
            l.setStroke(Color.BLACK);
           l.setStrokeWidth(1.7);
             Text labl;
            if(i==0){
                      labl = new Text((x/2)-8, y+(2*height/3) ,label.get(i)+"");
                         labl.setStyle("-fx-font-size:15px;-fx-fill:red;");
            }
            else{
                    double X = ((x-positions.get(i-1))/2)+ positions.get(i-1);
                     labl = new Text(X-8, y+(2*height/3) ,label.get(i)+"");
                    labl.setStyle("-fx-font-size:15px;-fx-fill:red;");
            }
           
            
            Text t = new Text(x-7,y+height+20,(start+ paritions.get(i)+last)+"");
           t.setStyle("-fx-font-size:15px");
           
            pane.getChildren().addAll(l , t , labl);
  
            last += paritions.get(i);
            
            
         }
           
        
         }else{
                      double x = (this.x+(split*( paritions.get(0)+last)))  ;
       
         
         positions.add(x);
            Line l= new Line(x,y,x,y+height);
            l.setStroke(Color.BLACK);
           l.setStrokeWidth(1.7);
             Text labl;
            
                      labl = new Text((x/2)-8, y+(2*height/3) ,label.get(0)+"");
                         labl.setStyle("-fx-font-size:15px;-fx-fill:red;");
           
            
            Text t = new Text(x-7,y+height+20,(start+ paritions.get(0)+last)+"");
           t.setStyle("-fx-font-size:15px");
           
            pane.getChildren().addAll(l , t , labl);
  
            //last += paritions.get(0);
           
                }
        
        
        
        if(paritions.size()!=1){
       Text labl = new Text(positions.get(positions.size()-1)+(((super.getWidth()+super.getX())-positions.get(positions.size()-1))/2)-8, y+(2*height/3) ,label.get(label.size()-1)+"");
         labl.setStyle("-fx-font-size:15px;-fx-fill:red;");
        
        Text t2  = new Text(x+width-15 , y+height +20 , (start+last+paritions.get(paritions.size()-1))+""); 
      t2.setStyle("-fx-font-size:15px");
        pane.getChildren().addAll(t0 ,t2,labl);
       }else
        {
       Text labl = new Text(positions.get(positions.size()-1)+(((super.getWidth()+super.getX())-positions.get(positions.size()-1))/2)-8, y+(2*height/3) ,label.get(label.size()-1)+"");
         labl.setStyle("-fx-font-size:15px;-fx-fill:red;");
        
        Text t2  = new Text(x+width-15 , y+height +20 , (start+last+paritions.get(paritions.size()-1))+""); 
      t2.setStyle("-fx-font-size:15px");
        pane.getChildren().addAll(t0 );
        
        }
        
        
    }
    
    
    public ArrayList<String> convertIT(ArrayList <Integer> list) {
        ArrayList <String> ans = new ArrayList<>() ; 
                
        for(int i = 0 ; i<list.size() ;++i){
            ans.add("P"+(list.get(i) ) ) ;
        }
                
           
        return ans ;
    }
    
  
    
   
    
}
