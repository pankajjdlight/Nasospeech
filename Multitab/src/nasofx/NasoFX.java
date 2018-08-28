/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;
//import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
//import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.stage.StageStyle.TRANSPARENT;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import static nasofx.FXMLDocumentController.classStage;
//import static nasofx.FXMLDocumentController.valuefromc;
//import static nasofx.FXMLDocumentController.valuefromc;
/**
 *
 * @author IITG
 */
public class NasoFX extends Application {
    VBox vb=new VBox();
    CheckMenuItem mi8,mi11,mi16,mi22,mi32,mi44,mi48,mi96;
   // Rectangle rect;
  //  SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
  //  SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
  //  SimpleDoubleProperty rectX = new SimpleDoubleProperty();
  //  SimpleDoubleProperty rectY = new SimpleDoubleProperty();
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    
    String mahadistance,error="";
    static String filenameinplot="";
    
    public int getrecordvalue=0;
    public boolean called=false;
    public PasteNasoFx pastenfx;
     private AudioFormat format;  
/**
     *
     */
        private AnchorPane objectsLayer;
    public StreamBytes streamBytes;
    public int startSam, endSam,startSample,endSample,totalSample;
    private int copy_from_ms = 0, copy_to_ms = 0, ann_fLength, ann_oldfLength,startPix,endPix; 
    public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
    public SignalProc sigProc;
    LineChart lineChart ;
    NumberAxis xAxis ;
// FXMLDocumentController fxmlobject =new FXMLDocumentController();
   static int Copied_Samples;
    
    
      public static int is_SaveAs_done=0;
      public static int tab_closed=0;
      public static int sr8=0,sr11=0,sr16=0,sr22=0,sr32=0,sr44=0,sr48=0,sr96=0;
      public static int donot8=0,donot11=0,donot16=0,donot22=0,donot32=0,donot44=0,donot48=0,donot96=0;
      double array[]={};
      int numSamples;
      float frameRate;
      int frameSize;
      int actual_frames_per_pixel;
      public  double frames_per_pixel;
      public double Duration;
      public int Is_record_done=0;
     
      public static int Is_record=0;
      public static int Is_resample=0;
     
      
    public static  double  valuefromc;
    public double hypernasality_value=0;
     XYChart.Data<Double,Double> dd;
     Plotwave plot;
// GridPane pane = new GridPane();
     Scene scene;
     Scene scene1;
     FXMLLoader primaryLoader;
     Capture cap = new Capture();
     public Button btn_to_pass=new Button();
     public  Line[] lines;
     public Line[] relines;
     Line[] lines1=new Line[10];
   //   Group test =new Group();
      Pane test =new Pane();
     // VBox test=new VBox();
      Rectangle re=new Rectangle();
      ScrollPane pane;
      Tab tab=new Tab();
       public  Media pick;  
    // public Media[] pick;
      // public MediaPlayer player ;
      public  TranslateTransition trans;
      public String multifilename="";
       Measurement mm=new Measurement();
       //public UIController documentcroller;
       
       
    public NasoFX() 
    {
        this.plot = new Plotwave();
        this.pastenfx = new PasteNasoFx();
        this.streamBytes = new StreamBytes();
         
    }
   
    
    @Override
    public void start(Stage stage) throws Exception  {
        primaryLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));  
        Parent textAreaHolder = (Parent) primaryLoader.load();
   // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));     
        
       
     // scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
       // scene.getStylesheets().add("test.css");
        
       stage.setResizable(false);
       stage.setMaxWidth(1215);
       stage.setMaxHeight(630);
       stage.setTitle(" NasoSpeech");
//        gp=new Group();
         
    //    rect = getNewRectangle();
     //   rect.widthProperty().bind(rectX.subtract(rectinitX));
      //  rect.heightProperty().bind(rectY.subtract(rectinitY));
       // vb.getChildren().add(textAreaHolder);
       // vb.getChildren().add(rect);
        
        scene = new Scene(textAreaHolder);
      //  scene1 = new Scene(textAreaHolder);
      //  scene.setOnMouseDragged(mouseHandler);
      //  scene.setOnMousePressed(mouseHandler);
      // scene.setOnMouseReleased(mouseHandler);
        
        stage.setScene(scene);
        //      gp.getChildren().add(rect);
       // lineChart.getData().add(rect);
    //    scene =new Scene(gp);  
          
        stage.centerOnScreen();
        stage.show();
    
    
     /*   stage.setOnHidden(e->{try {
            
        shutdown();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });   
        */
    /*  stage.setOnCloseRequest(e->{try {
            shutdown();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            }
});*/
    
    
    ///////////////////correct shut down//////////////////
    
   stage.setOnHiding(new EventHandler<WindowEvent>() {
    //    String val=getfilename();
         @Override
         public void handle(WindowEvent event) {
             Platform.runLater(new Runnable() {

                 @Override
                 public void run() {
                     try {
                         // System.out.println("Application Closed by click to Close Button(X)");
                         // System.exit(0);
                           // String val=plot.getfilename();
                            stage.show();
                            shutdown();
                            Platform.setImplicitExit(false);
                            event.consume();
                     } catch (UnsupportedAudioFileException ex) {
                         Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
         }
     }); 
 
    
    /////////////correct shut down///////////////////
    
   
    }
   public void sendfilenameinplot(String filename){
   
    filenameinplot=filename;
   }
   public String getfilename(){
   
   return filenameinplot;
   }
    
    public void setmenuitem8(CheckMenuItem ls){
    mi8=ls;
    }
    public void setmenuitem11(CheckMenuItem ls){
    mi11=ls;
    }
    
    public void setmenuitem16(CheckMenuItem ls){
    mi16=ls;
    }
   
    public void setmenuitem22(CheckMenuItem ls){
    mi22=ls;
    }
    public void setmenuitem32(CheckMenuItem ls){
    mi16=ls;
    }
    public void setmenuitem44(CheckMenuItem ls){
    mi44=ls;
    }
   
    public void setmenuitem48(CheckMenuItem ls){
    mi44=ls;
    }
   
    public void setmenuitem96(CheckMenuItem ls){
    mi96=ls;
    }
    
    
    
    
public void shutdown() throws UnsupportedAudioFileException, IOException, InterruptedException
{
    
///////////////////////show alert to save for recording as sir said///////////////////          
// System.out.println("inside shutdown"+ Is_record);
 //System.out.println("inside shutdown"+ Is_resample);
                             String val=getfilename();
                            java.nio.file.Path p=Paths.get(val);
                            String filename= p.getFileName().toString();
                            System.out.println("filename is"+filename);
if(is_SaveAs_done==0 && Is_record==1)
        
        {
         
          alertAction();
          
         }
         else
            
         {
           /*
             if(sr8==1||sr11==1||sr16==1||sr22==1||sr32==1||sr44==1||sr48==1||sr96==1){
            alertActionForResample();
            */
             if (sr8==1&&donot8==0){
    alertActionForResample();
    }
    if (sr11==1&&donot11==0){
    alertActionForResample();
    }
    if (sr16==1&&donot16==0){
    alertActionForResample();
    }
    if (sr22==1&&donot22==0){
    alertActionForResample();
    }
    if (sr32==1&&donot32==0){
    alertActionForResample();
    }
    if (sr44==1&&donot44==0){
    alertActionForResample();
    }if (sr48==1&&donot48==0){
    alertActionForResample();
    }
    if (sr96==1&&donot96==0){
    alertActionForResample();
    }
            
            
             }//
            Platform.exit();
             

        }

 ///////////alert button action/////////// 
/*private static class FixedOrderButtonDialog extends DialogPane {
    @Override
    protected Node createButtonBar() {
        ButtonBar node = (ButtonBar) super.createButtonBar();
        node.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);
        return node;
    }
} 
*/




public void alertAction() throws UnsupportedAudioFileException, IOException{
/*
    if(Is_resample==1){
    Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog ");
alert.setHeaderText("Do you want to save the changes to");
alert.setContentText("Choose your option.");

ButtonType buttonTypeDont = new ButtonType("Don't");

ButtonType buttonTypeSave = new ButtonType("Save as");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeDont, buttonTypeSave, buttonTypeCancel);
//alert.setDialogPane(new FixedOrderButtonDialog());
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeDont){
    if (Is_record==1){}
    else{
    Platform.exit();}
} else if (result.get() == buttonTypeSave) {
    
    //////////////////////////////////////do//////////////
  //nasofx.FXMLDocumentController.classStage.setScene(scene);
 // nasofx.FXMLDocumentController.classStage.showAndWait();\
  AfterResampleSave();
  if(Is_record==1){
  AfterRecordSave();
  }   
  Platform.exit();
    
   
    
    
    ///////////////save the file/////////////////////
} else 
{
  alert.close();
 // nasofx.FXMLDocumentController.classStage.setScene(scene);
 // nasofx.FXMLDocumentController.classStage.show();
  
  
 
}
}
 */   
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog ");
alert.setHeaderText("Do you want to save untitled.wav?");
alert.setContentText("Choose your option.");

ButtonType buttonTypeDont = new ButtonType("Don't");
ButtonType buttonTypeSave = new ButtonType("Save as");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeDont, buttonTypeSave, buttonTypeCancel);
//alert.setDialogPane(new FixedOrderButtonDialog());
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeDont){
    if (sr8==1){
    alertActionForResample();
    }
    if (sr11==1){
    alertActionForResample();
    }
    if (sr16==1){
    alertActionForResample();
    }
    if (sr22==1){
    alertActionForResample();
    }
    if (sr32==1){
    alertActionForResample();
    }
    if (sr44==1){
    alertActionForResample();
    }if (sr48==1){
    alertActionForResample();
    }
    if (sr96==1){
    alertActionForResample();
    }
    
    
    
    Platform.exit();
} else if (result.get() == buttonTypeSave) {
    
    //////////////////////////////////////do//////////////
  //nasofx.FXMLDocumentController.classStage.setScene(scene);
 // nasofx.FXMLDocumentController.classStage.showAndWait();
  AfterRecordSave();
  if (sr11==1){
    alertActionForResample();
    }
  Platform.exit();
    
   
    
    
    ///////////////save the file/////////////////////
} else 
{
    if (sr11==1){
    alertActionForResample();
    }
  alert.close();
 // nasofx.FXMLDocumentController.classStage.setScene(scene);
 // nasofx.FXMLDocumentController.classStage.show();
  
  
 
}
}

/////after resample save////


           //////////////////////////////////////////
public void alertActionForResample() throws UnsupportedAudioFileException, IOException{
java.nio.file.Path p=Paths.get(getfilename());
String substring= p.getFileName().toString();
    
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog ");
alert.setHeaderText("Do you want to save the changes made to");
alert.setContentText("Choose your option.");

ButtonType buttonTypeDont = new ButtonType("Don't");
ButtonType buttonTypeSave = new ButtonType("Save as");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeDont, buttonTypeSave, buttonTypeCancel);
//alert.setDialogPane(new FixedOrderButtonDialog());
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeDont)
{
   // Platform.exit();Delete the file
   AfterDontAction();
   alert.close();
   
   ////////do action for checking wheter more file is there or not
  
   
} else if (result.get() == buttonTypeSave)
{
    if(tab_closed==1)
        AfterResampleSaveForTab();
    else{
        AfterResampleSave();
    }
   
   alert.close();
  
    ///////////////save the file/////////////////////
} else 
{
  alert.close();
 
}


}
      public void AfterDontAction()
      {
      if (sr8==1&&donot8==0){
          //sr8=0;
          donot8=1;
          return;
        ////////////And delete the respective files/////////////////
    }
    if (sr11==1&&donot11==0){
        
  //  sr11=0;
  //if (donot11==1)return;
        donot11=1;
         return;
    }
    if (sr16==1&&donot16==0){
       donot16=1;
        return;
  //  sr16=0;
    }
    if (sr22==1&&donot22==0){
        donot22=1;
        return;
   // sr22=0;
    }
    if (sr32==1&&donot32==0){
       donot32=1;
        return;
   // sr32=0;
    }
    if (sr44==1&&donot44==0){
       donot44=1;
        return;
   // sr44=0;
    }if (sr48==1&&donot48==0){
        donot48=1;
        return;
   // sr48=0;
    }
    if (sr96==1&&donot96==0){
        donot96=1;
        return;
   // sr96=0;
    }
      
      
      
      }


///////savefile after record//////
   public String AfterRecordSave() throws UnsupportedAudioFileException, IOException{
    String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                     //   String cexedir = currentDir + "\\src\\"+"\\Icons\\";
                        String cexedi=currentDir+"\\cexe\\";
                  
        String filename=cexedi+"record.wav";
        String saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
         is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
       return saveLocation;
    
   
   
   
   }
   
   public String AfterResampleSave() throws UnsupportedAudioFileException, IOException{
       
    String currentDir = System.getProperty("user.dir");
    String saveLocation="";
    
    
    
       
    if(sr8==1&& donot8==0){
        String filename=currentDir+"\\convertedsr8.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
     //   System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr8=0;
       
    if (sr11==1){
    alertActionForResample();
    }
    if (sr16==1){
    alertActionForResample();
    }
    if (sr22==1){
    alertActionForResample();
    }
    if (sr32==1){
    alertActionForResample();
    }
    if (sr44==1){
    alertActionForResample();
    }if (sr48==1){
    alertActionForResample();
    }
    if (sr96==1){
    alertActionForResample();
    }
    }
     if(sr11==1&& donot11==0){
        String filename=currentDir+"\\convertedsr11.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr11=0;
        ////after this pop up should come for next saving
    if (sr8==1 &&donot8==0){
    alertActionForResample();
    }
    if(sr11==1 && donot11==0){
    alertActionForResample();
    }
    if (sr16==1 && donot16==0){
    alertActionForResample();
    }
    if (sr22==1 && donot22 ==0){
    alertActionForResample();
    }
    if (sr32==1 && donot32==0){
    alertActionForResample();
    }
    if (sr44==1 && donot44==0){
    alertActionForResample();
    }if (sr48==1 && donot48==0){
    alertActionForResample();
    }
    if (sr96==1 && donot96==0){
    alertActionForResample();
    }
    }
      if(sr16==1&& donot16==0){
        String filename=currentDir+"\\convertedsr16.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr16=0;
      if (sr8==1 &&donot8==0){
    alertActionForResample();
    }
    if(sr11==1 && donot11==0){
    alertActionForResample();
    }
    if (sr16==1 && donot16==0){
    alertActionForResample();
    }
    if (sr22==1 && donot22 ==0){
    alertActionForResample();
    }
    if (sr32==1 && donot32==0){
    alertActionForResample();
    }
    if (sr44==1 && donot44==0){
    alertActionForResample();
    }if (sr48==1 && donot48==0){
    alertActionForResample();
    }
    if (sr96==1 && donot96==0){
    alertActionForResample();
    }
   }
       if(sr22==1&& donot22==0){
        String filename=currentDir+"\\convertedsr22.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr22=0;
   if (sr8==1 &&donot8==0){
    alertActionForResample();
    }
    if(sr11==1 && donot11==0){
    alertActionForResample();
    }
    if (sr16==1 && donot16==0){
    alertActionForResample();
    }
    if (sr22==1 && donot22 ==0){
    alertActionForResample();
    }
    if (sr32==1 && donot32==0){
    alertActionForResample();
    }
    if (sr44==1 && donot44==0){
    alertActionForResample();
    }if (sr48==1 && donot48==0){
    alertActionForResample();
    }
    if (sr96==1 && donot96==0){
    alertActionForResample();
    }
    }
        if(sr32==1&& donot32==0){
        String filename=currentDir+"\\convertedsr32.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr32=0;
     if (sr8==1 &&donot8==0){
    alertActionForResample();
    }
    if(sr11==1 && donot11==0){
    alertActionForResample();
    }
    if (sr16==1 && donot16==0){
    alertActionForResample();
    }
    if (sr22==1 && donot22 ==0){
    alertActionForResample();
    }
    if (sr32==1 && donot32==0){
    alertActionForResample();
    }
    if (sr44==1 && donot44==0){
    alertActionForResample();
    }if (sr48==1 && donot48==0){
    alertActionForResample();
    }
    if (sr96==1 && donot96==0){
    alertActionForResample();
    }
   
    }
         if(sr44==1&&donot44==0){
        String filename=currentDir+"\\convertedsr44.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr44=0;
    if (sr8==1 &&donot8==0){
    alertActionForResample();
    }
    if(sr11==1 && donot11==0){
    alertActionForResample();
    }
    if (sr16==1 && donot16==0){
    alertActionForResample();
    }
    if (sr22==1 && donot22 ==0){
    alertActionForResample();
    }
    if (sr32==1 && donot32==0){
    alertActionForResample();
    }
    if (sr44==1 && donot44==0){
    alertActionForResample();
    }if (sr48==1 && donot48==0){
    alertActionForResample();
    }
    if (sr96==1 && donot96==0){
    alertActionForResample();
    }
    }
          if(sr48==1&&donot48==0){
        String filename=currentDir+"\\convertedsr48.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr48=0;
     if (sr8==1 &&donot8==0){
    alertActionForResample();
    }
    if(sr11==1 && donot11==0){
    alertActionForResample();
    }
    if (sr16==1 && donot16==0){
    alertActionForResample();
    }
    if (sr22==1 && donot22 ==0){
    alertActionForResample();
    }
    if (sr32==1 && donot32==0){
    alertActionForResample();
    }
    if (sr44==1 && donot44==0){
    alertActionForResample();
    }if (sr48==1 && donot48==0){
    alertActionForResample();
    }
    if (sr96==1 && donot96==0){
    alertActionForResample();
    }
    }
           if(sr96==1&&donot96==0){
        String filename=currentDir+"\\convertedsr96.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr96=0;
    if (sr8==1 &&donot8==0){
    alertActionForResample();
    }
    if(sr11==1 && donot11==0){
    alertActionForResample();
    }
    if (sr16==1 && donot16==0){
    alertActionForResample();
    }
    if (sr22==1 && donot22 ==0){
    alertActionForResample();
    }
    if (sr32==1 && donot32==0){
    alertActionForResample();
    }
    if (sr44==1 && donot44==0){
    alertActionForResample();
    }if (sr48==1 && donot48==0){
    alertActionForResample();
    }
    if (sr96==1 && donot96==0){
    alertActionForResample();
    }
    }
   
   return saveLocation;
   
   }
    
   ///////////////////////////////////////////////////// 
   public String AfterResampleSaveForTab() throws UnsupportedAudioFileException, IOException{
   
     String currentDir = System.getProperty("user.dir");
    String saveLocation="";
    
    
    
       
    if(sr8==1&& donot8==0){
        String filename=currentDir+"\\convertedsr8.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
     //   System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr8=0;
   }
     if(sr11==1&& donot11==0){
        String filename=currentDir+"\\convertedsr11.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr11=0;
        ////after this pop up should come for next saving
        return saveLocation;
    }
      if(sr16==1&& donot16==0){
        String filename=currentDir+"\\convertedsr16.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr16=0;
        return saveLocation;
   }
       if(sr22==1&& donot22==0){
        String filename=currentDir+"\\convertedsr22.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr22=0;
  
    }
        if(sr32==1&& donot32==0){
        String filename=currentDir+"\\convertedsr32.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr32=0;
     
   
    }
         if(sr44==1&&donot44==0){
        String filename=currentDir+"\\convertedsr44.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr44=0;
  
    }
          if(sr48==1&&donot48==0){
        String filename=currentDir+"\\convertedsr48.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr48=0;
   
    }
           if(sr96==1&&donot96==0){
        String filename=currentDir+"\\convertedsr96.wav\\";
         saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        /////
       
       //  is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
        sr96=0;
   
    }
   
   return saveLocation;
   
   
   
   
   }
   
   
   
   
 ////////////////////rect for dragging/////////////////   
    private Rectangle getNewRectangle() {
        Rectangle r = new Rectangle();
        r.setFill(Color.web("blue", 0.1));
       // r.setStroke(Color.BLUE);
       // r.setArcHeight(40);
       // r.setArcWidth(40);
        return r;
    }
    
    ////////////////////mousehandler////////////////////////////
/* EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {

            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.out.println("mouse is pressed inside eventhandler");
                rect.setX(mouseEvent.getX());
                rect.setY(mouseEvent.getY());
                rectinitX.set(mouseEvent.getX());
                rectinitY.set(mouseEvent.getY());
                
                
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                System.out.println("mouse is dragged inside eventhandler");
                rectX.set(mouseEvent.getX());
                rectY.set(mouseEvent.getY());
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                // Clone the rectangle
                System.out.println("mouse is realsed inside eventhandler");
                
                Rectangle r = getNewRectangle();
                r.setX(rect.getX());
                r.setY(rect.getY());
                r.setWidth(rect.getWidth());
                r.setHeight(rect.getHeight());
  //modify here pane.getChildren().add(r);
              //  vb.getChildren().add(r);

                // Hide the rectangle
                rectX.set(0);
                rectY.set(0);
            }
        }
    };
    */
    /////////////////////////mousehandler////////////////////
    
//////////////////////////Test mousehandler///////////////////////////////// 
 EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {

            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.out.println("mouse is pressed inside eventhandler");
              //  rect.setX(mouseEvent.getX());
              //  rect.setY(mouseEvent.getY());
              //  rectinitX.set(mouseEvent.getX());
              //  rectinitY.set(mouseEvent.getY());
                System.out.println("pressed"+mouseEvent.getX());
                //scene.fillProperty().set(Color.YELLOW);
                 Rectangle r = new Rectangle(100,200,300,400);
                 r.setFill(Color.YELLOW);
                 
                
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                System.out.println("mouse is dragged inside eventhandler");
                System.out.println("dragged"+mouseEvent.getX());
             //   rectX.set(mouseEvent.getX());
            //    rectY.set(mouseEvent.getY());
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                // Clone the rectangle
                System.out.println("mouse is realsed inside eventhandler");
                
              //  Rectangle r = getNewRectangle();
              //  r.setX(rect.getX());
              //  r.setY(rect.getY());
              //  r.setWidth(rect.getWidth());
              //  r.setHeight(rect.getHeight());
  //modify here pane.getChildren().add(r);
              //  vb.getChildren().add(r);

                // Hide the rectangle
              //  rectX.set(0);
              //  rectY.set(0);
            }
        }
    };
 
 
 
 
 
 
 
 
 
 
 
 
 //////////////////////////Test mousehandler/////////////////////////////////
 
 
 ///////////////////
    
    public void startforplotwave(Stage stage,double[] samples,int numsamples,String filename,Tab tab1 ,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration,AudioInputStream audioInputStream,float frameSize,int actual_frames_per_pixel) throws Exception 
    {
     //   System.out.println("duration as frame_per_pixel"+duration);
        xAxis= new NumberAxis("",0d,duration,0.05);
       NumberAxis yAxis = new NumberAxis("", -1d, 1d, 1);
       lineChart= new LineChart(xAxis, yAxis);
         java.nio.file.Path p=Paths.get(filename);
      
       String substring= p.getFileName().toString();
       stage.setTitle(substring);

        
        yAxis.tickMarkVisibleProperty();
       
        
        //XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
      //  XYChart.Data<Integer,Double> data = new XYChart.Data<>();
       
final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
   
     
      
      
       for(int i=0;i<numsamples;i++){
      
         // data = new XYChart.Data<Integer,Double>( i, samples[i]);
         
         
         dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         
         //dd.setNode(new HoveredThresholdNode(samples[i]));
         data.add(dd);
         // data.add(new XYChart.Data<>(i/sam_freq, samples[i]).setNode(new HoveredThresholdNode(samples[i])));
          
         
          //dataSeries1.getData().add(data);
         // System.out.println(samples[i]);
     
       }
     //dataSeries1.getData().add(data);
     XYChart.Series series = new XYChart.Series(data);
  
    // XYChart.Series=new XYChart.Series<>
     //xAxis.setUpperBound(2000);
     //xAxis.setLayoutX(factor);
     //series.setName("yyyyyy");
    // xAxis.autosize();
    xAxis.setSide(Side.TOP);
   
     lineChart.setCreateSymbols(false);
     
     lineChart.getData().clear();
     //lineChart.getData().add(10, series);
    // int index=346/2;
    // lineChart.getData().add(150, series);
     lineChart.getData().add(series);  
     
     
     
     
        System.out.println("upper   "+yAxis.getUpperBound()+"lower     "+yAxis.getLowerBound());
    
     lineChart.setLegendVisible(false);
       boolean verticalGridLinesVisible = lineChart.getVerticalGridLinesVisible();
      verticalGridLinesVisible=true;
      boolean horizontalGridLinesVisible = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible=true;
        
      lineChart.setCreateSymbols(false);
      lineChart.setAnimated(false);
     // lineChart.verticalGridLinesVisibleProperty(true){
    // public final void setVerticalGridLinesVisible(boolean value) { verticalGridLinesVisible.set(value); }
    
  //  }
      // xAxis.setTickUnit(factor);
     // lineChart.getYAxis().setTickLabelsVisible(false);
    //  lineChart.getYAxis().setTickMarkVisible(false);
    //lineChart.getXAxis().toString();
     //lineChart.getXAxis().setTickMarkVisible(false);  
     // lineChart.getXAxis().setTickLabelsVisible(false);
    //  lineChart.getXAxis().setOpacity(0);
      //lineChart.getYAxis().lookup(".axis-minor-tick-mark").setVisible(false);
     // lineChart.getXAxis().lookup(".axis-minor-tick-mark").setVisible(false);
    //  lineChart.getYAxis().setVisible(false);
     // lineChart.getYAxis().setTickLabelsVisible(false);
   //  lineChart.getYAxis().setOpacity(0);
        tab1.setText(substring);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    
   //lineChart.setPrefHeight(402);
     lineChart.setPrefWidth(1180);
     lineChart.setMinWidth(1180);
     //lineChart.setPrefHeight(352);
     //lineChart.setMinHeight(352);
   
         double height = lineChart.getHeight();//System.out.println("linechartheight"+height);
     //lineChart.setMaxSize(1300, 402);
   // lineChart.setMinSize(1300,402);
    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("test.css").toExternalForm());
    // wavepane.setPannable(true);
    // wavepane.setStyle(".scroll-pane > .viewport{-fx-background-color:#232323 ;\n" +
     //"-fx-control-inner-background: transparent;}");
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     Platform.setImplicitExit(false);

     wavepane.setContent(lineChart);
     
   
     
     lineChart.setOnMouseClicked(new EventHandler<MouseEvent>() 
     {  
      @Override public void handle(MouseEvent mouseEvent) {
          double xxx=(double)xAxis.getValueForDisplay(mouseEvent.getX());
          System.out.println("xaxis value print-->"+
          String.format(
            "x =.2f",xxx  
          )
        );
        //  xAxis.animatedProperty().asString();
          //lineChart.getXAxis().getLabel();
          
          
      // xAxis.setClip(new HoveredThresholdNode(xxx));
  //HoveredThresholdNode xxx=new HoveredThresholdNode();  
//xxx.
   //dd.setNode(new Label("xxx"));
        //  final Label label=new Label("xxxxx");
        //  lineChart.getEffect().notify();
//xAxis.getChildren().setAll(label);
//StackPane ss=new StackPane();
//ss.getChildren().add(ss);
// xAxis.getAnimated();
          //   xAxis.setCursor(Cursor.CROSSHAIR);
          // ss.toFront();
          // label.toFront();
         // boolean hover = xAxis.isHover();
// System.out.println("series data-->"+series.getData().toString());////////////////gives the data
   
      
     
      
      }
    });
   //  lineChart.setOnDragOver(new EventHandler<DragEvent>() {
  //  public void handle(DragEvent event) {
        /* data is dragged over the target */
        /* accept it only if it is not dragged from the same node 
         * and if it has a string data */
       /* if (event.getGestureSource() != target &&
                event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses 
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        */
    //  if(mousePosX1==0){
    //  mousePosX1=event.getX();
    //      System.out.println("position1"+mousePosX1);
    //  }
    //  else{
       //   mousePosX2=event.getX();
       //   System.out.println("position2"+mousePosX2);
      
    //  }
       
       
      
       
       
      //  event.consume();
    //}
//});

     
     lineChart.setOnMouseDragged(new EventHandler<MouseEvent>() 
    {  
        
      // lineChart.getData().clear();
        
      @Override public void handle(MouseEvent mouseEvent) {
          //double xxx=(double)xAxis.getValueForDisplay(mouseEvent.getX());
         // System.out.println("xaxis value print-->"+
         // String.format(
          //  "x =",xxx  
         // )
         
       if(mousePosX1==0){
     mousePosX1=mouseEvent.getX();
    // mouseEvent.consume();
         // System.out.println("position1"+mousePosX1);
     }
     else{
         mousePosX2=mouseEvent.getX();
        //  System.out.println("position2"+mousePosX2);
     
     }
       
          System.out.println("\nposition1->\t"+mousePosX1);
          System.out.println("\nposition2->\t"+mousePosX2);
          getSamplingPositions(mousePosX1,mousePosX2,(int)frameSize,actual_frames_per_pixel);
                startSample = getStartSamples();
                            endSample = getEndSamples();
String input="\nStarting MousePosition-->\n"+mousePosX1+"\nMouse_End_Position-->\n"+mousePosX2+"\nStartSample-->\n"+startSample+"\nendSample-->\n"+endSample;
System.out.print("\nStartSample\t"+startSample+"\nendSample\t"+endSample);
        //lineChart.getData().clear();
 
//////////////write to the file for articulation

 String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "/cexe/";
                        String filename=cexedir+"Input_For_Articulation.txt";


 File file = new File(filename);
  
          try {
              //Create the file
              if (file.createNewFile()){
                  System.out.println("File is created!");
              }else{
                  System.out.println("File already exists.");
              }
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
           
          //Write Content
          FileWriter writer = null;
          try {
              writer = new FileWriter(file);
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              writer.write(input);
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              writer.close();
              
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
              
  /////end of the file writer////////////////            
                  
              
// CutAudioWave cutW = new CutAudioWave();
 //cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

 //if (cutW.getresultByteArray() == null) {
   //  System.out.println("i have entered");
     //return;
// }
 //if (CutAudioInputStream.getCutWave() == null) {
   //  System.out.println("i have entered");
    // return;
 //}
          
   // String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                    //    String cexedir = currentDir + "/cexe/";
                    //    System.out.println("correct format"+cexedir);
                       // JFrame jf = new JFrame("test");
                        //String name = JOptionPane.showInputDialog(jf,
                        //currentDir, null);
                        
                 
                        
                        String filelocName=cexedir+"temp.wav";
                        StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(),audioInputStream , filelocName);
                        CutAudioInputStream.setCutWave(null);
                        
                        /////////////hypernasality////////////
  //CheckHypernasality(filelocName);
                        //////////////hypernasality//////////
                       
 
          
          
          
      }
    });

      
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         
     
     
     
     
     
     
     
     
     
     
     
     
     /*
    xAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
          System.out.println("xaxis value print-->"+
          String.format(
            "x = %.2f",
            xAxis.getValueForDisplay(mouseEvent.getX())
          )
        );
      }
    });
     
  */   
     
     
     
     
     
 /*    
     lineChart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                mousePosX1=e.getX();
                mousePosY1=e.getY();
                
                
                
                
                
                
               getSamplingPositions(e.getX(),e.getY(),numsamples,duration);
               int startSample = getStartSamples();
                            int endSample = getEndSamples();
                            System.out.print("StartSample\t"+startSample+"endSample\t"+endSample);
                                CutAudioWave cutW = new CutAudioWave();
                                cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) {
                                     System.out.println("i have entered");
                                     return;
                                }
                                if (CutAudioInputStream.getCutWave() == null) {
                                     System.out.println("i have entered");
                                     return;
                                }
                //System.out.println("mousePosX1--->"+e.getX());
               // System.out.println("mousePosY1---->"+e.getY());
               // series.getData().add(new XYChart.Data(series.getData().size() * 10, 30 + 50 * new Random().nextDouble()));
                //Coords();
            }
        });*/
     // System.out.println("mousePosX1--->"+mousePosX1);
     //System.out.println("mousePosX1--->"+e.getX());
     //wavepane.setBackground(new Background(Array(new BackgroundFill(Color.DARKCYAN,new CornerRadii(0),Insets(0)))));
    // wavepane.setContent().removeAll(lineChart);
    /// wavepane.getChildren().add(lineChart);
     
     
     

    wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
    /* scrollbar.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> ov,
            Number old_val, Number new_val) {
            
         //  double val= scrollbar.getValue();
            
          //  lineChart.setTranslateX((-val)*lineChart.getScaleX()*2);
            
          //  System.out.println(lineChart.getScaleX());
            
            double trans = lineChart.getBaselineOffset();
                lineChart.setTranslateX((-new_val.doubleValue()*20));
                System.out.println(trans);
                
        }
    });
   */  
     
     
     
     
   // lineChart.setScaleX(2.0);
    }
    public void tempplot(Stage stage,String filename,double[] samples,int numsamples,Tab tab1,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration )throws Exception{
        
        TP.getSelectionModel().select(tab1);
        getrecordvalue=1;
        called=true;
        
        xAxis= new NumberAxis("",0d,duration,0.05);
    //    xAxis.setAutoRanging(true);//("",0d,duration,0.05);
   //     xAxis.setForceZeroInRange(false);
       
        NumberAxis yAxis = new NumberAxis("", -1d, 1d, 1);
        
        lineChart= new LineChart(xAxis, yAxis);
        lineChart.setMaxHeight(330);
        lineChart.setMinHeight(330);
        java.nio.file.Path p=Paths.get(filename);
      
        String substring= p.getFileName().toString();
       // stage.setTitle(substring);
        tab1.setText(substring);  
        
        yAxis.tickMarkVisibleProperty();
       
        
        //XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
      //  XYChart.Data<Integer,Double> data = new XYChart.Data<>();
       
   final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
   
     
      
      
       for(int i=0;i<numsamples;i++){
      
         // data = new XYChart.Data<Integer,Double>( i, samples[i]);
         
         
         dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         
         //dd.setNode(new HoveredThresholdNode(samples[i]));
         data.add(dd);
         // data.add(new XYChart.Data<>(i/sam_freq, samples[i]).setNode(new HoveredThresholdNode(samples[i])));
          
         
          //dataSeries1.getData().add(data);
         // System.out.println(samples[i]);
     
       }
     //dataSeries1.getData().add(data);
     XYChart.Series series = new XYChart.Series(data);
  
    // XYChart.Series=new XYChart.Series<>
     //xAxis.setUpperBound(2000);
     //xAxis.setLayoutX(factor);
     //series.setName("yyyyyy");
    // xAxis.autosize();
     xAxis.setSide(Side.TOP);
     lineChart.setCreateSymbols(false);
     
     lineChart.getData().clear();
     //lineChart.getData().add(10, series);
    // int index=346/2;
    // lineChart.getData().add(150, series);
     lineChart.getData().add(series);  
     System.out.println("upper   "+yAxis.getUpperBound()+"lower     "+yAxis.getLowerBound());
    
     lineChart.setLegendVisible(false);
       boolean verticalGridLinesVisible = lineChart.getVerticalGridLinesVisible();
      verticalGridLinesVisible=true;
      boolean horizontalGridLinesVisible = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible=true;
        
      lineChart.setCreateSymbols(false);
      lineChart.setAnimated(false);
     // lineChart.verticalGridLinesVisibleProperty(true){
    // public final void setVerticalGridLinesVisible(boolean value) { verticalGridLinesVisible.set(value); }
    
  //  }
      // xAxis.setTickUnit(factor);
     // lineChart.getYAxis().setTickLabelsVisible(false);
    //  lineChart.getYAxis().setTickMarkVisible(false);
    //lineChart.getXAxis().toString();
     //lineChart.getXAxis().setTickMarkVisible(false);  
     // lineChart.getXAxis().setTickLabelsVisible(false);
    //  lineChart.getXAxis().setOpacity(0);
      //lineChart.getYAxis().lookup(".axis-minor-tick-mark").setVisible(false);
     // lineChart.getXAxis().lookup(".axis-minor-tick-mark").setVisible(false);
    //  lineChart.getYAxis().setVisible(false);
     // lineChart.getYAxis().setTickLabelsVisible(false);
   //  lineChart.getYAxis().setOpacity(0);
//        tab1.setText(substring);
     TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    
   //lineChart.setPrefHeight(402);
     lineChart.setPrefWidth(1180);
     lineChart.setMinWidth(1180);
     //lineChart.setPrefHeight(352);
     //lineChart.setMinHeight(352);
   
         double height = lineChart.getHeight();//System.out.println("linechartheight"+height);
     //lineChart.setMaxSize(1300, 402);
   // lineChart.setMinSize(1300,402);
    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("test.css").toExternalForm());
    // wavepane.setPannable(true);
    // wavepane.setStyle(".scroll-pane > .viewport{-fx-background-color:#232323 ;\n" +
     //"-fx-control-inner-background: transparent;}");
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     Platform.setImplicitExit(false);
   //  wavepane.setContent(lineChart);
     //wavepane.setContent(rect);
 // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        rect = getNewRectangle();
   //     rect.widthProperty().bind(rectX.subtract(rectinitX));
   //     rect.heightProperty().bind(rectX.subtract(rectinitY));
        Group group=new Group();
        group.getChildren().addAll(lineChart);
        wavepane.setContent(group);
        wavepane.setPannable(false);
   
 
     wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());

/////////////////////// start of the final  MouseListener//////////////////////////////////////
Rectangle r = getNewRectangle();
lineChart.addEventHandler(MouseEvent.MOUSE_PRESSED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//             rect.setX(mouseEvent.getX());
             //   rect.setY(mouseEvent.getY());
      //       rectinitX.set(mouseEvent.getX());
             // rectinitY.set(mouseEvent.getY());
    //        
      //       rectX.set(0);
     //        rectY.set(0);
            // rect.setHeight(320);
                 
            mousePosX1=mouseEvent.getX();
          
            mousePosX2=0;
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX1);
            double doubleValue = valueForDisplay.doubleValue();
            plot.setMouseStartpos(doubleValue);
            
            int width=1135;
            double  StartSecond= ((duration/width)*mouseEvent.getX());
     
        //   System.out.println("Startsecondduration------------------>\t"+StartSecond);
           plot.setStartSecond(StartSecond);
     
            
            
            double d=doubleValue*sam_freq;
            plot.setStartSample(d);
            System.out.println("startposition"+xAxis.getValueForDisplay(mousePosX1)+"start_time"+mousePosX1+"startsample"+d);
            
            }
        });
lineChart.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
        //       rectX.set(mouseEvent.getX());
         //      rectY.set(mouseEvent.getY());
//orgSceneX = mouseEvent.getSceneX();
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
lineChart.addEventHandler(MouseEvent.MOUSE_RELEASED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // double offsetX = event.getSceneX()- orgSceneX;
        
                
         //       r.setX(rect.getX());
          //      r.setY(rect.getY());
          //      r.setWidth(rect.getWidth());
            //    r.setHeight(rect.getHeight());
            
            mousePosX2=event.getX();
            //  mousePosX1=0;    
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX2);
            double doubleValue = valueForDisplay.doubleValue();
            double d=doubleValue*sam_freq;
           // double lastduration=doubleValue/duration;
           
             double width=1135;
          double factor=(duration*1000)/width;
                System.out.println("factor"+factor);
          double movefactor=event.getX()*factor;////calculate duration
         int milli=(int)movefactor%1000;
         String mm=String.valueOf(milli);
          
      int second=(int)movefactor/1000;
        String ss=String.valueOf(second);    
         
        
      //  double duration = plot.getduration();
   double  LastSecond= ((duration/width)*event.getX());
     
   //  System.out.println("lastsecondduration-------------------->\t"+LastSecond);
  
        
        plot.setLastSecond(LastSecond);
        
          
            System.out.println("lastduration"+movefactor+"\t"+ss+"\t"+mm+"duration\n"+duration+"\n");
            plot.setEndSample(d);
            plot.setMouseEndpos(doubleValue);
           // System.out.println("\nendposition"+xAxis.getValueForDisplay(mousePosX2)+"endsample"+d);
           // System.out.println("total sample\t"+plot.getSamplingPositions());
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
           System.out.println(""+input+"\nCopied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
           generate_text_file(input);
                



// Hide the rectangle
              // rectX.set(0);
             //  rectY.set(0);// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

//////////////////////////END Of the final mouselistener///////////////////////////////////

     
     
     
     
     
     
   /* lineChart.setOnDragDetected(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) 
            {  mousePosX1=event.getX();
               mousePosX2=0;           
               setStartSample(mousePosX1);
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    } 
    );
        
   */ 
   
//pane.setHgap(10);
//pane.setVgap(10);
//VBox root = new VBox();
//root.getChildren().add(pane);
//scene = new Scene(root);
//lineChart.setOnMouseDragged(mouseHandler);
//lineChart.setOnMousePressed(mouseHandler);
//lineChart.setOnMouseReleased(mouseHandler);
//rect = getNewRectangle();
//rect.widthProperty().bind(rectX.subtract(rectinitX));
//rect.heightProperty().bind(rectY.subtract(rectinitY));
//vb.getChildren().add(rect);
//lineChart.getData().add(rect);
//wavepane.setContent(rect);///////add the rect 





/*
lineChart.addEventHandler(MouseEvent.DRAG_DETECTED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            mousePosX1=e.getX();
            mousePosX2=0;
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX1);
            double doubleValue = valueForDisplay.doubleValue();
            plot.setMouseStartpos(doubleValue);
            double d=doubleValue*sam_freq;
            plot.setStartSample(d);
            System.out.println("startposition"+xAxis.getValueForDisplay(mousePosX1)+"startsample"+d);
          // Rectangle r=new Rectangle(0,0,1180,350);
          // wavepane.setContent(r);
         //  scene =new Scene(r);
         //  stage.setScene(scene);
         //  stage.show();
            
        }
});
   */
  /* lineChart.addEventHandler(MouseEvent.MOUSE_RELEASED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) 
        {
            mousePosX2=e.getX();
            mousePosX1=0;    
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX2);
            double doubleValue = valueForDisplay.doubleValue();
            double d=doubleValue*sam_freq;
            plot.setEndSample(d);
            plot.setMouseEndpos(doubleValue);
           // System.out.println("\nendposition"+xAxis.getValueForDisplay(mousePosX2)+"endsample"+d);
           // System.out.println("total sample\t"+plot.getSamplingPositions());
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
           System.out.println("Copied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
           generate_text_file(input);
           
        }

            

           
       });
     
     */   
    
  } 
    
    
    
    
    
    LineChart lineChart1;
    
     public void tempplot1(Stage stage,String filename,double[] samples,int numsamples,Tab tab1,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration, int check)throws Exception{
        TP.getSelectionModel().select(tab1);
        xAxis= new NumberAxis();
       xAxis.setAutoRanging(true);//("",0d,duration,0.05);
       xAxis.setForceZeroInRange(false);
        NumberAxis yAxis = new NumberAxis("", -1d, 1d, 1);
      
        lineChart1= new LineChart(xAxis, yAxis);
        java.nio.file.Path p=Paths.get(filename);
      
        String substring= p.getFileName().toString();
      // stage.setTitle(substring);
        tab1.setText(substring);  
     //  lineChart.setMaxHeight(170);
       
       
       
       if(check==0){
            lineChart= new LineChart(xAxis, yAxis);
        
        yAxis.tickMarkVisibleProperty();
       
        
        //XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
      //  XYChart.Data<Integer,Double> data = new XYChart.Data<>();
       
   final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
   
     XYChart.Series series = new XYChart.Series( );
       lineChart.setCreateSymbols(false);
     
     lineChart.getData().clear();
      
     lineChart.getData().add(series); 
      
       for(int i=0;i<numsamples;i++){
     
         dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         data.add(dd);
          
          
       }
      //data.remove(0,5000);
       series.setData(data);
       //series.getData().remove(0, 2000);
       
    
       //series.getData().setAll(dd);
       //series.setData(data);
              
       
       
     //dataSeries1.getData().add(data);
     
  
    // XYChart.Series=new XYChart.Series<>
     //xAxis.setUpperBound(2000);
     //xAxis.setLayoutX(factor);
     //series.setName("yyyyyy");
    // xAxis.autosize();
    xAxis.setSide(Side.TOP);
   
    
     //
     
     
     
     
     
        System.out.println("upper   "+yAxis.getUpperBound()+"lower     "+yAxis.getLowerBound());
    
     lineChart.setLegendVisible(false);
       boolean verticalGridLinesVisible = lineChart.getVerticalGridLinesVisible();
      verticalGridLinesVisible=true;
      boolean horizontalGridLinesVisible = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible=true;
        
      lineChart.setCreateSymbols(false);
      lineChart.setAnimated(false);
     // lineChart.verticalGridLinesVisibleProperty(true){
    // public final void setVerticalGridLinesVisible(boolean value) { verticalGridLinesVisible.set(value); }
    
  //  }
      // xAxis.setTickUnit(factor);
     // lineChart.getYAxis().setTickLabelsVisible(false);
    //  lineChart.getYAxis().setTickMarkVisible(false);
    //lineChart.getXAxis().toString();
     //lineChart.getXAxis().setTickMarkVisible(false);  
     // lineChart.getXAxis().setTickLabelsVisible(false);
    //  lineChart.getXAxis().setOpacity(0);
      //lineChart.getYAxis().lookup(".axis-minor-tick-mark").setVisible(false);
     // lineChart.getXAxis().lookup(".axis-minor-tick-mark").setVisible(false);
    //  lineChart.getYAxis().setVisible(false);
     // lineChart.getYAxis().setTickLabelsVisible(false);
   //  lineChart.getYAxis().setOpacity(0);
//        tab1.setText(substring);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    
   //lineChart.setPrefHeight(402);
     lineChart.setPrefWidth(1180);
     lineChart.setMinWidth(1180);
     
    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
     wavepane.setContent(lineChart);
     
       }
     
     else if(check==1){
         
       
     lineChart.setMaxHeight(170);
     //lineChart.setPrefWidth(50);
     lineChart.setMinHeight(170);////////key step of resampling
     
       xAxis= new NumberAxis("",0d,duration,0.05);
       NumberAxis yaxis = new NumberAxis("", -1d, 1d, 1);
            
       java.nio.file.Path p1=Paths.get(filename);
       String substring1= p1.getFileName().toString();
     //  stage.setTitle(substring);
       tab1.setText(substring);  
       yAxis.tickMarkVisibleProperty();
       
       final   ObservableList<XYChart.Data<Double,Double>> data1 = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
    
       for(int i=0;i<numsamples;i++){
       
         dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         
         data1.add(dd);
        
       }
   
     XYChart.Series series1 = new XYChart.Series(data1);
  
    
    xAxis.setSide(Side.BOTTOM);
   
     lineChart1.setCreateSymbols(false);
     
     lineChart1.getData().clear();
      
     lineChart1.getData().add(series1); 
  ///////////////////////////////////////////////////    
   ///////////////////////////////////////////////////   
     
     
     
        System.out.println("upper   "+yAxis.getUpperBound()+"lower     "+yAxis.getLowerBound());
    
     lineChart1.setLegendVisible(false);
       boolean verticalGridLinesVisible1 = lineChart1.getVerticalGridLinesVisible();
      verticalGridLinesVisible1=true;
      boolean horizontalGridLinesVisible1 = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible1=true;
        
      lineChart1.setCreateSymbols(false);
      lineChart1.setAnimated(false);
      lineChart1.setStyle(this.getClass().getResource("test.css").toExternalForm());
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     Platform.setImplicitExit(false);
     VBox root = new VBox();
     root.getChildren().addAll(lineChart1,lineChart);
        
 //       lineChart1.setMaxHeight(170);
      //  lineChart.setMaxHeight(170);
    //    root.getChildren().add(lineChart);
    //    root.setMinHeight(340);
   //     root.setPrefHeight(340);
   //     root.setMaxHeight(340);
       // root.fillWidthProperty().set(false);
        
        wavepane.setContent(root);
        
      // wavepane.setPannable(true);
     }
  
       
       final double SCALE_DELTA = 1.1;
lineChart.setOnScroll(new EventHandler<ScrollEvent>() {
    public void handle(ScrollEvent event) {
        event.consume();

        if (event.getDeltaY() == 0) {
            return;
        }

        double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

        lineChart.setScaleX(lineChart.getScaleX() * scaleFactor);
        //lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
    }
});

lineChart.setOnMousePressed(new EventHandler<MouseEvent>() {
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
            lineChart.setScaleX(1.0);
            lineChart.setScaleY(1.0);
        }
    }
});

final double SCALE_DELTA1 = 1.1;
lineChart1.setOnScroll(new EventHandler<ScrollEvent>() {
    public void handle(ScrollEvent event) {
        event.consume();

        if (event.getDeltaY() == 0) {
            return;
        }

        double scaleFactor1 = (event.getDeltaY() > 0) ? SCALE_DELTA1 : 1 / SCALE_DELTA1;

        lineChart1.setScaleX(lineChart1.getScaleX() * scaleFactor1);
        //lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
    }
});

lineChart1.setOnMousePressed(new EventHandler<MouseEvent>() {
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
            lineChart1.setScaleX(1.0);
            lineChart1.setScaleY(1.0);
        }
    }
});


//////check////





   /*  final NumberAxis axis = (NumberAxis) lineChart.getXAxis();
        final double lowerX = axis.getLowerBound();
        final double upperX = axis.getUpperBound();
     
     
      lineChart.setOnScroll(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                final double minX = axis.getLowerBound();
                final double maxX = axis.getUpperBound();
                double threshold = minX + (maxX - minX) / 2d;
                double x = event.getX();
                double value = axis.getValueForDisplay(x).doubleValue();
                double direction = event.getDeltaY();
                if (direction > 0) {
                    if (maxX - minX <= 1) {
                        return;
                    }
                    if (value < threshold) {
                        axis.setLowerBound(minX + 1);
                    } else {
                        axis.setUpperBound(maxX - 1);
                    }
                } else {
                    if (value < threshold) {
                        double nextBound = Math.max(lowerX, minX - 1);
                        axis.setLowerBound(nextBound);
                    } else {
                        double nextBound = Math.min(upperX, maxX + 1);
                        axis.setUpperBound(nextBound);
                    }
                }

            }
        });

      */
    
      
 // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
 //    wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());

   /* lineChart.setOnDragDetected(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) 
            {  mousePosX1=event.getX();
               mousePosX2=0;           
               setStartSample(mousePosX1);
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    } 
    );
        
   */ 
   
//pane.setHgap(10);
//pane.setVgap(10);
//VBox root = new VBox();
//root.getChildren().add(pane);
//scene = new Scene(root);
//lineChart.setOnMouseDragged(mouseHandler);
//lineChart.setOnMousePressed(mouseHandler);
//lineChart.setOnMouseReleased(mouseHandler);
//rect = getNewRectangle();
//rect.widthProperty().bind(rectX.subtract(rectinitX));
//rect.heightProperty().bind(rectY.subtract(rectinitY));
//lineChart.getData().add(rect);
//wavepane.setContent(rect);///////add the rect 

/*Rectangle dragBox = new Rectangle(0, 0, 0, 0);
dragBox.setVisible(false);
lineChart.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
            dragBox.setVisible(true);
            dragBox.setTranslateX(mouseEvent.getX());
            dragBox.setTranslateY(mouseEvent.getY());
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED && dragBox.isVisible()){
            dragBox.setWidth(mouseEvent.getX() - dragBox.getTranslateX());
            dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
            dragBox.setVisible(false);
        System.out.println("Rectangle is created.......");
        XYChart.Data data = new XYChart.Data();
        data.setNode(dragBox);
        
        
        XYChart.Series series1 = new XYChart.Series();
            
            series1.getData().add(data);
            lineChart.getData().add(series1);
        }
    }
});

 
*/


/*
lineChart.addEventHandler(MouseEvent.DRAG_DETECTED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            mousePosX1=e.getX();
            mousePosX2=0;
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX1);
            double doubleValue = valueForDisplay.doubleValue();
            plot.setMouseStartpos(doubleValue);
            double d=doubleValue*sam_freq;
            plot.setStartSample(d);
            System.out.println("startposition"+xAxis.getValueForDisplay(mousePosX1)+"startsample"+d);
           // Rectangle r=new Rectangle(0,0,1180,350);
           // wavepane.setContent(r);
           //stage.setScene(scene);
           //stage.show();

        }
});
   
   lineChart.addEventHandler(MouseEvent.MOUSE_RELEASED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) 
        {
            mousePosX2=e.getX();
            mousePosX1=0;    
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX2);
            double doubleValue = valueForDisplay.doubleValue();
            double d=doubleValue*sam_freq;
            plot.setEndSample(d);
            plot.setMouseEndpos(doubleValue);
           // System.out.println("\nendposition"+xAxis.getValueForDisplay(mousePosX2)+"endsample"+d);
           // System.out.println("total sample\t"+plot.getSamplingPositions());
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           generate_text_file(input);      
           
        }

           
       });
     
        }
    
  */} 
    
    
  public void SeT_No_Of_Samples_Copied(int setSample) {
            Copied_Samples=setSample;
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }  
    
    
    public int Get_No_Of_Samples_Copied(){
    return Copied_Samples;    
    }
    
    
    
    
    
    
    
    
    
  private void generate_text_file(String input) {
         
//////////////write to the file for articulation

 String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
  String cexedir = currentDir + "//cexe//";
                        String filename=cexedir+"Input_For_Articulation.txt";


File file = new File(filename);
  
          try {
              //Create the file
              if (file.createNewFile()){
                  System.out.println("File is created!");
              }else{
                  System.out.println("File already exists.");
              }
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
           
          //Write Content
          FileWriter writer = null;
          try {
              writer = new FileWriter(file);
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              writer.write(input);
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              writer.close();
              
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
              
  /////////////end of the file writer////////////////            
           
        



    
    
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
    
    
    
    
    
    
     public void paint() 
    { //System.out.println("i am here");
      //  Graphics2D g2 = (Graphics2D) g;
        
       // Draw Selection portion 
                      //  Color mouseDraggedbg = new Color(255, 255, 153);
                         
                        //java.awt.Color mouseDraggedbg = new java.awt.Color(51, 153, 255);
                        
                       // g2.setColor(mouseDraggedbg);
                        
       // objectsLayer = new AnchorPane();
      //  objectsLayer.prefHeightProperty().bind(heightProperty());
      ///  objectsLayer.prefWidthProperty().bind(widthProperty());
        
        
        
        
        
                         mousePosX1=0;
                         mousePosX2=1180;
                        int widthPos1 = (int) (mousePosX1 - mousePosX2);
                        int minValue1 = (int) (widthPos1 < 0 ? mousePosX1 : mousePosX2);
                        widthPos1 = (widthPos1 < 0 ? -1 * widthPos1 : widthPos1);
                        System.out.println("widthPos1\t"+widthPos1+"minValue1\t"+minValue1);
                        Rectangle r=new Rectangle((int) minValue1,0,1180,350);
                        //lineChart.setStyle("");
                        r.setFill(Color.AQUA);
                     //   objectsLayer.getChildren().remove(r);
                      //  objectsLayer.getChildren().add(r);
                      //  lineChart.getData().add(objectsLayer);
                      
                        if (minValue1 != 0) {
                            r.setFill(Color.CYAN);
                           // g2.fillRect((int) minValue1, 0, 1180, 350);/// 0 to 1180 x,y,width=1180,height=352
                            System.out.println("mousePosY1"+mousePosY1);
                            if (mousePosY1 > 195) {
                                try {
                                    BufferedImage image2 = ImageIO.read(new File("conf/img/L_01.jpg"));
                                  //  g2.drawImage(image2, (int) mousePosX2, (int) h - INFOPAD - 2, null);
                                  //  samplingGraph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                } catch (IOException ex) {
                                  //  Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
    
    
    
    
    }
    
    public void saveas(AudioInputStream audioInputStream,int frameSize,int actual_frames_per_pixel)//audioinputstream,numsamples,duration
    {
       /*
          if (this.mousePosX1 != 0 && this.mousePosX2 != 0) 
                            {
                                this.getSamplingPositions(mousePosX1, mousePosX2, frameSize,actual_frames_per_pixel);//getSamplingPositions();
                                int startSample = this.getStartSamples();//getStartSamples();
                                int endSample = this.getEndSamples();//getEndSamples();
                                
                                
                                 byte[] audioBytes = StreamConverter.streamTobyte(audioInputStream);
                                   if (audioBytes == null)
                                   {
                                        audioInputStream = null;
                                        return;
                                   }
                                 streamBytes.setCurrent(audioBytes);
                                   audioBytes = null;
                                  audioInputStream = StreamConverter.byteTostream(streamBytes.getCurrent(), audioInputStream);
                                 
                                 
                                System.out.print("StartSample in saveas\t"+startSample+"endSample in saveas\t"+endSample);
                                CutAudioWave cutW = new CutAudioWave();
                             //  streamBytes.setCurrent(audioBytes);
                                cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) 
                                {
                                     System.out.println("i have entered in saveas getresult");
                                     return;
                                }
                                if (CutAudioInputStream.getCutWave() == null)
                                {
                                     System.out.println("i have entered in saveas");
                                     return;
                                }
                                 //String currentDir = System.getProperty("user.dir");
                                 //System.out.println("cu");
                                 //String cexedir = currentDir + "\\cexe\\";
                                // createTempFile("wave",".wav"," C:\\Users\\user\\Documents\\NetBeansProjects\\HypernasalityFinal\\");
                                String filelocName =saveLocation();//"C:\\Users\\user\\Documents\\NetBeansProjects\\HypernasalityFinal\\temp.wav";//saveLocation(); //drawingComponent.this.ReturnFilename;saveLocation();
                                System.out.println("filelocName Saved\t"+filelocName);
                                StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(),audioInputStream, filelocName);

                                CutAudioInputStream.setCutWave(null);
    }
         */
         
         
         
     
                               
        

        
        
        
        
        
        
        
        
        
    
    }
    ///////////copy////////////////
    
            //Copy
   
public void copy(AudioInputStream audioInputStream,int frameSize,int actual_frames_per_pixel,float framerate)
{         
            if (this.mousePosX1 != 0 && this.mousePosX2 != 0 && audioInputStream != null) 
            {
                
                        try {
                            //  cutWave();
                            if (this.mousePosX1 == 0 && this.mousePosX2 == 0) {
                                return;
                            }
                            copy_from_ms = 0;
                            copy_to_ms = 0;
                                 this.getSamplingPositions(this.mousePosX1, this.mousePosX2, frameSize,actual_frames_per_pixel);//getSamplingPositions();////////////////correct frame_pre_pixel
                                 int startSample = this.getStartSamples();//getStartSamples();
                                 int endSample = this.getEndSamples();//getEndSamples();
                                 
                                 byte[] audioBytes = StreamConverter.streamTobyte(audioInputStream);
                                   if (audioBytes == null)
                                   {
                                        audioInputStream = null;
                                        return;
                                   }
                                 streamBytes.setCurrent(audioBytes);
                                   audioBytes = null;
                                  audioInputStream = StreamConverter.byteTostream(streamBytes.getCurrent(), audioInputStream);
                                 
                                 
                                 
                                 
                                 
                            System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramesPerPix " + actual_frames_per_pixel+"framerate"+framerate);

                            CutAudioWave cutW = new CutAudioWave();
                            cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

                         //   right.calculatePixcel();
                              if (this.mousePosX1 > this.mousePosX2) 
                              {
                                  startPix = (int) this.mousePosX2;
                                  endPix = (int) this.mousePosX1;
                              } else
                              {
                                  startPix = (int) this.mousePosX1;
                                  endPix = (int) this.mousePosX2;
                              }
                            
                            
                            
                            
                            
                            
                            
                            PixcelConversion pixConversion = new PixcelConversion();
                            copy_from_ms = pixConversion.pixcelToMillisecond(startPix, actual_frames_per_pixel, (int) framerate);////////frame_per correct it
                            copy_to_ms = pixConversion.pixcelToMillisecond(endPix, actual_frames_per_pixel, (int) framerate);
                            System.out.println("copy_from_ms\t"+copy_from_ms+"copy_to_ms\t"+copy_to_ms);

                            if (cutW.getresultByteArray() == null) {
                                System.out.println("\ncopyresultbytearry null");
                                return;
                            }



                        } 
                        catch (Exception ex) 
                        {
                        //    Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                        }
           }
}
            //End copy
        
       public void paste(AudioInputStream audioInputStream,Tab tab1 ,TabPane TP,ScrollPane wavepane) throws Exception
     { 
         
     
   if (CutAudioInputStream.getCutWave() != null && audioInputStream != null) {
               
                        if (CutAudioInputStream.getCutWave() != null) {
                            AudioInputStream newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), audioInputStream);

                            // FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), pWave.fileName, copy_from_ms, copy_to_ms);
                          //  ArrayList ann_id = new FindAnnotated((Connection) pWave.mainFrame, Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), copy_from_ms, copy_to_ms).getAnnotationID();

                            String newAnn_file = Hash.getHashValue(newInputStream);
                            newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), audioInputStream);
                           // new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(newAnn_file, copy_from_ms, 0, ann_id);
                          // pWave.mainFrame.createCopyPanel("Copy/Paste", newInputStream);
                          int size = (int) newInputStream.getFrameLength()*2;
                          byte audioData [] = new byte[size];
                          System.out.println("ffffff = "+  newInputStream.getFrameLength() + "  " + newInputStream.getFormat());
 /////ask about this read function                         int bytesRead = newInputStream.read(audioData);
            //               System.out.println("bytes read = "+ bytesRead);
                           format = audioInputStream.getFormat();
                           double sampling_freq=format.getSampleRate();
                         numSamples = (int) newInputStream.getFrameLength();
                           System.out.println("audio data length"+audioData.length);
   // frames_per_pixel=audioData.length/1292;
     //   System.out.println("frames_per_pixel"+frames_per_pixel);
                          double samples[] = readAudioData(audioData);
                           long audioFileLength = audioData.length+44;//new File( filename).length();/////////////////////////adding 44 to match new File() length
                         frameSize = format.getFrameSize();
                          frameRate = format.getFrameRate();
                       frames_per_pixel = (audioFileLength / (frameSize * frameRate));///////in fact this is duration
                      actual_frames_per_pixel=audioData.length/1180/2;
                       System.out.println("actual_frames_per_pixel\t"+actual_frames_per_pixel);
                       String filename="copy&Paste";
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                         pastenfx.startforplotwave(classStage,samples,numSamples,filename,tab1,TP,wavepane,sampling_freq,frames_per_pixel,newInputStream,frameSize,actual_frames_per_pixel);
                            CutAudioInputStream.setCutWave(null);
                        }
                    }
               
            
     }  
    
        
 double[] readAudioData(byte audioBytes[]){
     
     
      int audioData[]  = {};
      if (format.getSampleSizeInBits() == 16) {
                    int nlengthInSamples = audioBytes.length / 2;
                    audioData = new int[nlengthInSamples];
                    if (format.isBigEndian()) {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i];
                            /* Second byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                        }
                    } else {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i];
                            /* Second byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                            
                            
                           /* File fi=new File("C:\\TATA-V-42\\src\\Speech\\WavePanel\\Au.txt");
	                    FileWriter fw=new FileWriter(fi,true);
                            BufferedWriter tout = new BufferedWriter(fw);
	                    tout.write(String.valueOf(audioData[i]));
                            tout.newLine();
                            tout.flush();
                            tout.close();  */
                            //System.out.println(audioData[i]);
                        }
                        
//calculating the maximum and minimum amplitude 
                           int maximum = audioData[0];   // start with the first value
                           for (int m=1; m<audioData.length; m++) {
                           if (audioData[m] > maximum) {
                           maximum = audioData[m];   // new maximum
                           
                            }
                        }
                           
                         
                           int minimum = audioData[0];   // start with the first value
                           for (int k=1; k<audioData.length; k++) {
                           if (audioData[k] < minimum) {
                           minimum = audioData[k];   // new maximum
                           
                            }
                        }
                           
                            }
                        }
                                
                                     
                                
                            
                           
                 else if (format.getSampleSizeInBits() == 8) {
                    int nlengthInSamples = audioBytes.length;
                    audioData = new int[nlengthInSamples];
                    if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i];

                        }
                    } else {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i] - 128;

                        }
                    }
                }
      
                    sigProc = new SignalProc();
                    double[] audioDataNormalize1 = sigProc.normalize(sigProc.intToDouble(audioData), 1400);
      
      return audioDataNormalize1;
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      public String saveLocation()
    {        
        
   String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("wave files","*.wav","*.wma","*.mp3"));
    
    File selectedfile=fc.showSaveDialog(classStage);
   if(selectedfile!=null)
   {
     System.out.println("file name"+selectedfile.getName());
   }
   
   return selectedfile.getAbsolutePath(); 
   
   
   
}   
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void showHover(Float value)
    {  
    
        
        
        
        
    
    
    }
    
    public void getSamplingPositions(double mousePosX1,double mousePosX2,int frameSize,int actual_frames_per_pixel) {
        try {
         //   System.out.println("duration in sampling position"+duration);
            int width = 1180;//pWave.samplingGraph.getSize().width;
            int inPos = (int) mousePosX1, enPos = (int) mousePosX2;

            if (inPos < 1) {
                inPos = 1;
            }
            if (enPos > (width - 10)) {
                enPos = width - 10;
            }
            if (enPos < 1) {
                enPos = 1;
            }
            if (inPos > (width - 10)) {
                inPos = width - 10;
            }

            int startTime = (int)((inPos *actual_frames_per_pixel) * frameSize);////////////it is not the duration it has to be changed ask 
            int endTime = (int)((enPos *actual_frames_per_pixel) * frameSize);
            int startSample, endSample;
            if (startTime > endTime) {
                startSample = endTime;
                endSample = startTime;
            } else {
                startSample = startTime;
                endSample = endTime;
            }
          //  System.out.println("startSample inside"+startSample);
           // System.out.println("endSample inside"+endSample);
            setStartSamples(startSample);
            setEndSamples(endSample);
        } catch (Exception er) {
            System.err.println(er);
        }

    }
    
     private void setStartSamples(int start) {
        this.startSam = start;
    }

    private void setEndSamples(int end) {
        this.endSam = end;
    }
    public int getStartSamples() {
        return this.startSam;
    }

    public int getEndSamples() {
        return this.endSam;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public double[] normalize(double []samples, int numsamples){
        double norm_arr[] = new double[numsamples] ;
        double max = Math.abs(samples[0]);
        /// find the max value
        for(int itr=1;itr<numsamples;itr++){
            if(Math.abs(samples[itr])> max )
                max = Math.abs(samples[itr]);
        }
        
        /// normalize with max value
        for(int itr=0;itr<numsamples;itr++){
            norm_arr[itr] = samples[itr]/max;
        }
        
        System.out.println("max = "+max   + "samples 0 = " + samples[0]  + "norm 0 = "+ norm_arr[0]);
        
        
        return norm_arr;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
        
        
        
    }
    
    //////////////////////////select&CheckHypernasality////////////////////////////
       private double CheckHypernasality(String filelocName,AnchorPane resultpane,Stage stage) 
       {
             
               try {        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                          Process p1;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_final_version_working",
                                    filelocName,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc_output_13dim.txt"
                            );
                          
                          
                          p1 = pb1.start();
                          System.out.println("fdgdfhdfhdfhdfhfdhdf");     
                          
                          p1.waitFor();
                         
                             
                          LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(cexedir+"mfcc_output_13dim.txt")));
                            lnr.skip(Long.MAX_VALUE);
                            System.out.println(lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
                                    // Finally, the LineNumberReader object should be closed to prevent resource leak
                            int numFrames = lnr.getLineNumber();
                         //   System.out.println("The number of frames is AAAAAAA"+numFrames);
                            lnr.close();
                            System.out.println("num frames = "+numFrames);
                                  ProcessBuilder pb = new ProcessBuilder(cexedir+"posteriorcomputation" ,
                                          cexedir+"mfcc_output_13dim.txt",
                                          cexedir+"mean_norm.txt",
                                          cexedir+"var_norm.txt",
                                          cexedir+"weight_norm.txt",
                                          cexedir+"mean_clp.txt",
                                          cexedir+"var_clp.txt",
                                          cexedir+"weight_clp.txt",
                                          cexedir+"output_norm.txt",
                                          cexedir+"output_clp.txt", "16", "13", Integer.toString(numFrames));
                                 // 
                                  //  ProcessBuilder pb = new ProcessBuilder("tree");
                                  
                                  
                                  try {
                                      //System.out.println("i am here");
                                      Process p = pb.start();
                                      /*try {
                                      pb.wait(0);
                                      } catch (InterruptedException ex) {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                      */
                              // s p.waitFor();
                              int waitFor = p.waitFor();
                              p.isAlive();
                                      System.out.println("wait for value"+waitFor);
                                   //   BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                      Scanner sc=new Scanner(p.getInputStream());
                                     valuefromc= Double.parseDouble(sc.next());
                                     
                                    // System.out.println("xxxxxxxxxxxxx---->"+br.readLine());
                                     //String st= br.readLine();
                                      //Double.parseDouble(br.readLine());
                                      //valuefromc=Float.parseFloat(br.readLine());
                                    //System.out.println("float of xxxxxxxx----->"+Double.parseDouble(br.readLine()));
                                    // valuefromc = Double.parseDouble(br.readLine());
                                      //(float) 0.27;//Double.parseDouble(br.readLine());//br.readLine();
                                     //System.out.println("%f i am  getting this value from c---->"+valuefromc);
                                      //br.readLine();
                                      
                                      //  System.out.println(" i am  getting this value from c---->");
                                      
                                      //System.out.println("value getting"+br);//br.readLine());
                                      // String probability =br.readLine();
                                      //double probnew = Double.parseDouble(probability);
                                      
                                                               
                                      //System.out.println(" i am  getting this value after converting from double---->"+valuefromc);
                                     // PlotProbability plot=new  PlotProbability();
                                      //plot.plotfunction();
                                     PlotProbability plot1=new  PlotProbability(this);
                                       plot1.plotfunction(resultpane,stage);
                                       plot1.setstageStage(stage);
                                    //  plot1.plotfunction1();
                                      //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
                                 
                                  }
                                  catch (IOException ex)
                                  {
                                    //  Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  
                                  
                      }
                      catch (IOException ex) 
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (InterruptedException ex)
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         }
                return valuefromc;
         }
             
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void dozoom(Float value,ScrollPane wavepane,double duration){
  //double i=1;
    // while(i>0){
    //lineChart.setStyle(this.getClass().getResource("zoom.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("zoom.css").toExternalForm());
   
    if(value<10)
    {
    this.lineChart.setMinWidth(1180);
    xAxis.setTickUnit(.05);
    
    //xAxis= new NumberAxis("", 0d, duration, .001);
    
    
    //scrollbar.setVisibleAmount(100);
    //scrollbar.setValue(50); 
      
 
    }
    else if(value>=10&&value<30){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.08);
     
     wavepane.setHvalue(.50);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=30&&value<50){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.05);
     
     wavepane.setHvalue(.40);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=50&&value<70){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.03);
     
     wavepane.setHvalue(.30);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=70&&value<90){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.02);
     
     wavepane.setHvalue(.20);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>90)
            {
                this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.01);
     
     wavepane.setHvalue(.10);
    }
    }
   /* public void dozoomout(){
  //   double i=1;
    // while(i>0){
   
      this.lineChart.setScaleX(1);
       xAxis.setTickUnit(1);
      //i++;
     //} //this.lineChart.onZoomProperty();
    
    }
*/
    double[] getdata(double[] samples) {
        System.out.println("i am entering\n");
        this.array=samples;
        // for(int i=0;i<13780;i++)
       //System.out.println(samples[i]);
        return this.array;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTab(Tab tab1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     private void setUpZooming(final Rectangle rect, final Node zoomingNode) {
        final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();
        zoomingNode.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseAnchor.set(new Point2D(event.getX(), event.getY()));
                rect.setWidth(0);
                rect.setHeight(0);
            }
        });
        zoomingNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                rect.setX(Math.min(x, mouseAnchor.get().getX()));
                rect.setY(Math.min(y, mouseAnchor.get().getY()));
                rect.setWidth(Math.abs(x - mouseAnchor.get().getX()));
                rect.setHeight(Math.abs(y - mouseAnchor.get().getY()));
            }
        });
    }
    
     
     
     
        
            
            
            

            private void doZoom(Rectangle zoomRect, LineChart<Number, Number> chart) {
        Point2D zoomTopLeft = new Point2D(zoomRect.getX(), zoomRect.getY());
        Point2D zoomBottomRight = new Point2D(zoomRect.getX() + zoomRect.getWidth(), zoomRect.getY() + zoomRect.getHeight());
        final NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        Point2D yAxisInScene = yAxis.localToScene(0, 0);
        final NumberAxis xAxis = (NumberAxis) chart.getXAxis();
        Point2D xAxisInScene = xAxis.localToScene(0, 0);
        double xOffset = zoomTopLeft.getX() - yAxisInScene.getX() ;
        double yOffset = zoomBottomRight.getY() - xAxisInScene.getY();
        double xAxisScale = xAxis.getScale();
        double yAxisScale = yAxis.getScale();
        xAxis.setLowerBound(xAxis.getLowerBound() + xOffset / xAxisScale);
        xAxis.setUpperBound(xAxis.getLowerBound() + zoomRect.getWidth() / xAxisScale);
        yAxis.setLowerBound(yAxis.getLowerBound() + yOffset / yAxisScale);
        yAxis.setUpperBound(yAxis.getLowerBound() - zoomRect.getHeight() / yAxisScale);
        System.out.println(yAxis.getLowerBound() + " " + yAxis.getUpperBound());
        zoomRect.setWidth(0);
        zoomRect.setHeight(0);
    }

            
 @FXML
 public double Hypernasality(String filename,AnchorPane resultpane,Stage stage){
 
 
    
                        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                        System.out.println("correct format"+cexedir);
                       // JFrame jf = new JFrame("test");
                        //String name = JOptionPane.showInputDialog(jf,
                        //currentDir, null);
                      try {
                          Process p1;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_final_version_working",
                                    filename,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc_output_13dim.txt"
                            );
                          
                          
                          p1 = pb1.start();
                          System.out.println("fdgdfhdfhdfhdfhfdhdf");     
                          
                          p1.waitFor();
                         
                             
                          LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(cexedir+"mfcc_output_13dim.txt")));
                            lnr.skip(Long.MAX_VALUE);
                            System.out.println(lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
                                    // Finally, the LineNumberReader object should be closed to prevent resource leak
                            int numFrames = lnr.getLineNumber();
                         //   System.out.println("The number of frames is AAAAAAA"+numFrames);
                            lnr.close();
                            System.out.println("num frames = "+numFrames);
                                  ProcessBuilder pb = new ProcessBuilder(cexedir+"posteriorcomputation" ,
                                          cexedir+"mfcc_output_13dim.txt",
                                          cexedir+"mean_norm.txt",
                                          cexedir+"var_norm.txt",
                                          cexedir+"weight_norm.txt",
                                          cexedir+"mean_clp.txt",
                                          cexedir+"var_clp.txt",
                                          cexedir+"weight_clp.txt",
                                          cexedir+"output_norm.txt",
                                          cexedir+"output_clp.txt", "16", "13", Integer.toString(numFrames));
                                 // 
                                  //  ProcessBuilder pb = new ProcessBuilder("tree");
                                  
                                  
                                  try {
                                      //System.out.println("i am here");
                                      Process p = pb.start();
                                      /*try {
                                      pb.wait(0);
                                      } catch (InterruptedException ex) {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                      */
                              // s p.waitFor();
                              int waitFor = p.waitFor();
                              p.isAlive();
                                      System.out.println("wait for value"+waitFor);
                                   //   BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                      Scanner sc=new Scanner(p.getInputStream());
                                     valuefromc= Double.parseDouble(sc.next());
                                     
                                    // System.out.println("xxxxxxxxxxxxx---->"+br.readLine());
                                     //String st= br.readLine();
                                      //Double.parseDouble(br.readLine());
                                      //valuefromc=Float.parseFloat(br.readLine());
                                    //System.out.println("float of xxxxxxxx----->"+Double.parseDouble(br.readLine()));
                                    // valuefromc = Double.parseDouble(br.readLine());
                                      //(float) 0.27;//Double.parseDouble(br.readLine());//br.readLine();
                                     //System.out.println("%f i am  getting this value from c---->"+valuefromc);
                                      //br.readLine();
                                      
                                      //  System.out.println(" i am  getting this value from c---->");
                                      
                                      //System.out.println("value getting"+br);//br.readLine());
                                      // String probability =br.readLine();
                                      //double probnew = Double.parseDouble(probability);
                                      
                                                               
                                      //System.out.println(" i am  getting this value after converting from double---->"+valuefromc);
                                     // PlotProbability plot=new  PlotProbability();
                                      //plot.plotfunction();
                                     PlotProbability plot1=new  PlotProbability(this);
                                       plot1.plotfunction(resultpane,stage);
                                       plot1.setstageStage(stage);
                                    //  plot1.plotfunction1();
                                      //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
                                 
                                  }
                                  catch (IOException ex)
                                  {
                                    //  Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  
                                  
                      }
                      catch (IOException ex) 
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (InterruptedException ex) {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
            return valuefromc;
 		
}
    public static double getvaluefromc()
    {
        return valuefromc;
    }
   
      public void selectAll(int numsamples,double duration)
              
{                    
    
                         
                         System.out.println("numsamples"+numsamples);
                         System.out.println("duration"+duration);
                 //////mouse drag paint////////        
            //     this.paint(g);
            
            
           
              
                try{
                           int inPos = 0;//(int) mousePosX1,
                           int enPos = 1180;//(int) ((pWave.samplingGraph.getSize().width) - 10);

                            if (inPos < 1) 
                            {
                                inPos = 1;
                            }

                            if (enPos < 1) 
                            {
                                enPos = 1;
                            }
                            if (inPos > (enPos - 10)) 
                            {
                                inPos = enPos - 10;
                            }
                            //double bytesPerSecond = pWave.audioInputStream.getFormat().getFrameSize() * (double) pWave.audioInputStream.getFormat().getFrameRate();
                            int startTime = (int)((inPos * duration) * numsamples);
                            int endTime = (int)((enPos * duration) * numsamples);
                           // System.out.println("CK " + ((inPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000);


                           //mousePosX1 = startTime;
                          // mousePosX2 = endTime;
                            System.out.println("starttime\t"+startTime+" endtime\t"+endTime+" mousestart\t"+inPos +" mouseend\t"+enPos);
                          //  pWave.samplingGraph.repaint();
                          
                
                          
                }
                
                
                catch (Exception er) 
                {
                            System.err.println(er);
                        
                }
                this.paint();
}

    void getrecord(int Is) {
        System.out.println("value in getrecord"+Is);
        getrecordvalue=Is;
        System.out.println("value in getrecord"+getrecordvalue);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   int getrecordvalue() {
      System.out.println("value from  getrecordvalue"+ getrecordvalue); 
      return  getrecordvalue;//=Is_record_done;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public int x=getrecordvalue();

               
 /////////////////select and check hypernasality///////////// 
       
 public String Select_Check_hypernalasityclick(String getfilename) throws UnsupportedAudioFileException, IOException{
      
      int Samples_Copied = this.Get_No_Of_Samples_Copied();     
      AudioInputStream inputStream = null;
      AudioInputStream shortenedStream = null;
      File file = new File(getfilename);
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
      AudioFormat format = fileFormat.getFormat();
      inputStream = AudioSystem.getAudioInputStream(file);
      int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
        //    inputStream.skip((long) (plot.getMouseStartpos()*bytesPerSecond));
        // inputStream.skip(Samples_Copied);
        double startSecond = mm.getStartSecond();
        double LastSecond=mm.getLastSecond();
        System.out.println("select_check_hypernasality"+startSecond+"\t"+LastSecond);
        long StartByte=(long) startSecond*bytesPerSecond ;
       inputStream.skip(StartByte);
      
      ////////////////////////////////////////////
      /* inputStream.skip(startSecond * bytesPerSecond);///startduration in seconds
      long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();///secondstocopy i.e.ending seconds
      shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
     */
      ///////////////////////////////////////////////
     long framesOfAudioToCopy;
     framesOfAudioToCopy = (long) LastSecond * (long)format.getFrameRate();
  //  long framesOfAudioToCopy=2;
     // long framesOfAudioToCopy =endSample;
    // System.out.println("startsecond\t"+plot.getStartSecond()+"LastSecond\t"+plot.getLastSecond()); 
     
     shortenedStream = new AudioInputStream(inputStream, format,framesOfAudioToCopy);
      String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                     //   String cexedir = currentDir + "\\src\\"+"\\Icons\\";
                        String cexedi=currentDir+"\\cexe\\";
                   
     
     String HypernasalityFile =cexedi+"Hypernasal.wav";//rightClick.saveLocation();                           later add record here
       File destinationFile = new File(HypernasalityFile);
      AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
    
     // Plotwave plot=new Plotwave();
      
////this line has changed due to change in multitab;later you change it     double[] samples = plot.readWaveData(HypernasalityFile);
     return HypernasalityFile;
     //RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.01);
     //duration=plot.getduration();
   //     factor=plot.getsampfrq();
        //numSamples=plot.getnumsamples();
    //                 double[] fpoints;
                    // fpoints= rdpf.filter(samples);
  //                   int numsamples=samples.length;
   //     double duration=numsamples/ factor;
        
  //     System.out.println(" file is created-->"+factor+numsamples+duration);       
        
        
 
  
  
  }     
    
    
//////////////////////////////////////////////////////    

    void drawLine(Stage classStage, Tab tabForPaste, TabPane TP, ScrollPane wavepaneForPaste,AnchorPane ap) {
        
        Group gp=new Group();
        TP.getSelectionModel().select(tabForPaste);
        Line line = new Line(100,10,10,110);
        Line line1 = new Line();
        line.setStroke(Color.GREEN);
        line.setStrokeWidth(10);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(10);
        Line line3=new Line();
        for(int i=1;i<100;i++){
       /* line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(100.0f);
        line.setEndY(100.0f);
         line.setStartX(1.0f);
        line.setStartY(1.0f);
        line.setEndX(200.0f);
        line.setEndY(200.0f);
        line.setStartX(2.0f);
        line.setStartY(2.0f);
        line.setEndX(300.0f);
        line.setEndY(300.0f);
        */
       // line.setStartX(i);
        line1.setStartX(10);
       // line.setStartY(2*i);
        line1.setStartY(100);
     //   line.setEndX(3*i);
        line1.setEndX(200);
       // line.setEndY(5*i);
        line1.setEndY(10);
        
    //    line.setStartX(100);
       // line.setStartY(2*i);
      ////  line.setStartY(10);
     //   line.setEndX(3*i);
      //  line.setEndX(200);
       // line.setEndY(5*i);
     //   line.setEndY(100);
       
        
        
        
        
        
        
       // line1.setStartX(i);
       // line1.setStartY(2*i);
       // line1.setEndX(3*i);
       // line1.setEndY(5*i);
    
        
        }
        
     //   line1.setStartX(100.0f);
      //  line1.setStartY(1.0f);
     //   line1.setEndX(200.0f);
     //   line1.setEndY(300.0f);
        
        
        
       VBox box = new VBox();
        Rectangle r = getNewRectangle();
       box.addEventHandler(MouseEvent.MOUSE_PRESSED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//             rect.setX(mouseEvent.getX());
             //   rect.setY(mouseEvent.getY());
   //          rectinitX.set(mouseEvent.getX());
             // rectinitY.set(mouseEvent.getY());
            System.out.println("ppppppppppppppppppppp");
    //         rectX.set(0);
    //         rectY.set(0);
            // rect.setHeight(320);
                 
            mousePosX1=mouseEvent.getX();
          
            mousePosX2=0;
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX1);
            double doubleValue = valueForDisplay.doubleValue();
            plot.setMouseStartpos(doubleValue);
            
            int width=1135;
       //     double  StartSecond= ((duration/width)*mouseEvent.getX());
     
        //   System.out.println("Startsecondduration------------------>\t"+StartSecond);
     //      plot.setStartSecond(StartSecond);
     
            
            
    //        double d=doubleValue*sam_freq;
   //         plot.setStartSample(d);
   //         System.out.println("startposition"+xAxis.getValueForDisplay(mousePosX1)+"start_time"+mousePosX1+"startsample"+d);
            
            }
        });
box.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
        //       rectX.set(mouseEvent.getX());
        //       rectY.set(mouseEvent.getY());
                System.out.println("hhhhdhshdhsdhkjhkjkhj");
//orgSceneX = mouseEvent.getSceneX();
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       line.addEventHandler(MouseEvent.MOUSE_RELEASED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // double offsetX = event.getSceneX()- orgSceneX;
        
                
//                r.setX(rect.getX());
   //             r.setY(rect.getY());
      //          r.setWidth(rect.getWidth());
    //            r.setHeight(rect.getHeight());
            
            mousePosX2=event.getX();
            //  mousePosX1=0;    
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX2);
            double doubleValue = valueForDisplay.doubleValue();
    //        double d=doubleValue*sam_freq;
           // double lastduration=doubleValue/duration;
           
             double width=1135;
   //       double factor=(duration*1000)/width;
   //             System.out.println("factor"+factor);
   //       double movefactor=event.getX()*factor;////calculate duration
   //      int milli=(int)movefactor%1000;
   //      String mm=String.valueOf(milli);
          
   //   int second=(int)movefactor/1000;
   //     String ss=String.valueOf(second);    
         
        
      //  double duration = plot.getduration();
//   double  LastSecond= ((duration/width)*event.getX());
     
   //  System.out.println("lastsecondduration-------------------->\t"+LastSecond);
  
        
//        plot.setLastSecond(LastSecond);
        
          
 //           System.out.println("lastduration"+movefactor+"\t"+ss+"\t"+mm+"duration\n"+duration+"\n");
 //           plot.setEndSample(d);
//            plot.setMouseEndpos(doubleValue);
           // System.out.println("\nendposition"+xAxis.getValueForDisplay(mousePosX2)+"endsample"+d);
           // System.out.println("total sample\t"+plot.getSamplingPositions());
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
           System.out.println(""+input+"\nCopied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
           generate_text_file(input);
                



// Hide the rectangle
              // rectX.set(0);
             //  rectY.set(0);// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
  //      rect = getNewRectangle();
  //      rect.widthProperty().bind(rectX.subtract(rectinitX));
  //      rect.heightProperty().bind(rectX.subtract(rectinitY));
        box.getChildren().addAll(line1,line);
        tabForPaste.setContent(box);
        
      //  wavepaneForPaste.setContent(box);












// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    void drawLine(Stage classStage, double[] samples, double factor,int numsamples,TabPane TP,String filename,double duration,Pane linepane ) {
     
        linepane.getChildren().clear();
        test.getChildren().clear();
        double samples_per_pixcel=numsamples/1215;
        
        plot=new Plotwave();
        
        //tab=new Tab(); 
        java.nio.file.Path p=Paths.get(filename);
        String substring= p.getFileName().toString();
        tab.setText(substring); 
        tab.setClosable(true);
        TP.getTabs().addAll(tab);
        
        pane =new ScrollPane();
        
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
       
   
        
        
     //   pane.setPannable(false);
     //   test.setStyle("-fx-background-color:#222222");
       
       // test.setMinSize(1215, 394);
      //  test.setPrefHeight(394);
       // test.setPrefWidth(1215);
       
        linepane.getChildren().addAll(test);
        
        pane.setContent(linepane);
         
       // ScrollPane pane1 =new ScrollPane();
        
      //  double samples_per_pixcel=1215/2;
        
        tab.setContent(pane);
        
        TP.getSelectionModel().select(tab);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
    
        
     /////vertical gridline///////   
        int j=120;
         for(int i=0;i<10;i++){
                   
         lines1[i]=new Line(j*i,0,j*i,394);
         
       linepane.getChildren().addAll(lines1[i]); 
        
   //      pane.setContent(test);
       //  pane1.setContent(test);
          }
        
       /////vertical gridline///////    
        
        
        
        
       
 test.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                plot.setMouseStartpos(event.getX());
                plot.setMousestartYpos(event.getY());
                re.setX(plot.getMouseStartpos());
                re.setY(plot.getMousestartYpos()-394);
                re.setHeight(778+plot.getMousestartYpos());
                re.setWidth(0);
                re.setFill(Color.BLUE);
                re.setOpacity(0.3);
                int width=1215;
                double  StartSecond= ((duration/width)*event.getX());  ////here factor is sam.freq
                plot.setStartSecond(StartSecond);
                double d=event.getX();
                System.out.println("startsecondddddd"+StartSecond+"duration"+duration+"width"+width+"event"+event.getX());
                plot.setStartSample(d);
          }
        });
 
 test.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                re.setWidth(event.getX()-plot.getMouseStartpos());
                //pane.getChildren().add(re);
               // pane.setContent(re);
              linepane.getChildren().add(re);
             //  pane.setContent(test);
               
            }
        });
 
test.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
             System.out.println("vcvcvcvc"+event.getX());
             double width=1215;
             double  LastSecond= ((duration/width)*event.getX());
             plot.setLastSecond(LastSecond);
              double d=event.getX()*16000;
             plot.setEndSample(d);
            plot.setMouseEndpos(d);
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
           System.out.println(""+input+"\nCopied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
           
        
                }
        });
         
      
        
        
        
   /////waveform display////  
     lines=new Line[numsamples];    
     for( int i=1;i<numsamples;i++){
 
//lines[i]=new Line((i)/samples_per_pixcel,samples[i]*150+197,((i+1))/samples_per_pixcel,samples[i+1]*150+197);////we can set 150 also for magnified amplitude
//lines[i]=new Line((i/samples)*785,samples[i]*225+197,((i+1)/samples)*785,samples[i+1]*225+197);//previous formula
 lines[i]=new Line(i/samples_per_pixcel,samples[i]*120+197,(i+1)/samples_per_pixcel,samples[i+1]*120+197);
 
 test.getChildren().addAll(lines[i]);
// lines[i].setStroke(Color.GREEN);
// pane.setContent(test); 
 //pane1.setContent(test);
 
     }
       // wavepaneForPaste.setContent(test);
///////
     }  
 
   /////////////////////////////////multitab////////////////////////////////////////////////////
    
    
     void test(Stage classStage, double[] samples, double factor,int numsamples,TabPane TP,String filename,double duration) throws MalformedURLException
     {
      //  Pane linepane=new Pane();
      //  linepane.getChildren().clear();
      //  test.getChildren().clear();////here test is a group
        double samples_per_pixcel=numsamples/1215;
        
        Plotwave plot=new Plotwave();
        
        //tab=new Tab(); 
        java.nio.file.Path p=Paths.get(filename);
        String substring= p.getFileName().toString();
        Tab tab=new Tab();
        
        
        tab.setText(substring); 
        tab.setClosable(true);
        TP.getTabs().addAll(tab);
        
        ScrollPane pane =new ScrollPane();
        
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
       
   
        
        
     //   pane.setPannable(false);
     //   test.setStyle("-fx-background-color:#222222");
       
       // test.setMinSize(1215, 394);
      //  test.setPrefHeight(394);
       // test.setPrefWidth(1215);
       
      //  linepane.getChildren().addAll(test);
        Pane test=new Pane();
    //    Group test=new Group();
        pane.setContent(test);
     //   AnchorPane an=new AnchorPane();
        //an.getChildren().add(pane);
       // ScrollPane pane1 =new ScrollPane();
        
      //  double samples_per_pixcel=1215/2;
        
       // tab.setContent(pane);
        tab.setContent(pane);
      //  TP.getSelectionModel().select(tab);
     /* 
        TP.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    @Override
    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
        // do something...
        System.out.println("vvvbbvbvbvbv"+filename);
        
    }

        });
     */
     /*
       SingleSelectionModel<Tab> selectionModel = TP.getSelectionModel();
     selectionModel.select(tab); //select by object
     selectionModel.select(1); //select by index starting with 0
     selectionModel.clearSelection();//clear your selection 
   
    
        tab.setOnSelectionChanged(new EventHandler<Event>() {
             
            @Override
            public void handle(Event event) {
                event.consume();
              //  TP.selectionModelProperty().set(selectionModel);
                // System.out.println("vvvbbvbvbvbv"+filename);
            
              Media pick;
                try {
                    pick = new Media(new File(filename).toURI().toURL().toExternalForm());
                    MediaPlayer player = new MediaPlayer(pick);
                    TranslateTransition  trans= new TranslateTransition();
                    ///setter method to be created
                    
                    setpick(filename);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                }
         
              // MediaPlayer player =
         // selectionModel.clearSelection();
            }
        });
     */
       // clearcheckmenuitem();
        setmultifilename(filename);
        setpick(filename);
        setfileduration(duration);
        tickmenuitem((float) factor);
        tab.setOnSelectionChanged(event -> {
        if (tab.isSelected()) {
            System.out.println("Tab is Selected"+filename);
            try {
                clearcheckmenuitem();
           //   mi8.setSelected(false);
                setpick(filename);
                setfileduration(duration);
                setmultifilename(filename);
                tickmenuitem((float) factor);
               // this.clearcheckmenuitem();
            } catch (MalformedURLException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
       
        
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
    
        
     /////vertical gridline///////   
        Line[] lines1=new Line[10];
        int j=120;
         for(int i=0;i<10;i++){
                   
         lines1[i]=new Line(j*i,0,j*i,350);
         
       test.getChildren().addAll(lines1[i]); 
        
   //      pane.setContent(test);
       //  pane1.setContent(test);
          }
        
       /////vertical gridline///////  
         /////waveform display////  
     Line[] lines=new Line[numsamples];      
     for( int i=1;i<numsamples-1;i++){
 
//lines[i]=new Line((i)/samples_per_pixcel,samples[i]*150+197,((i+1))/samples_per_pixcel,samples[i+1]*150+197);////we can set 150 also for magnified amplitude
//lines[i]=new Line((i/samples)*785,samples[i]*225+197,((i+1)/samples)*785,samples[i+1]*225+197);//previous formula
 lines[i]=new Line(i/samples_per_pixcel,samples[i]*100+190,(i+1)/samples_per_pixcel,samples[i+1]*100+190);
 //test.getChildren().add(new Line(120,120,80,90));
 test.getChildren().addAll(lines[i]);
// lines[i].setStroke(Color.GREEN);
// pane.setContent(test); 
 //pane1.setContent(test);
 
     }
        
        

 test.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                plot.setMouseStartpos(event.getX());
                plot.setMousestartYpos(event.getY());
                re.setX(plot.getMouseStartpos());
                re.setY(plot.getMousestartYpos()-394);
                re.setHeight(778+plot.getMousestartYpos());
                re.setWidth(0);
                re.setFill(Color.BLUE);
                re.setOpacity(0.3);
                int width=1215;
                double  StartSecond= ((duration/width)*event.getX());  ////here factor is sam.freq
                plot.setStartSecond(StartSecond);
                double d=event.getX();
                System.out.println("startsecondddddd"+StartSecond);
                plot.setStartSample(d);
                mm.setStartSecond(StartSecond);
          }
        });
 
 test.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                re.setWidth(event.getX()-plot.getMouseStartpos());
                //pane.getChildren().add(re);
               // pane.setContent(re);
          //    linepane.getChildren().add(re);
             //  pane.setContent(test);
               test.getChildren().add(re);
            }
        });
 
test.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            // System.out.println("vcvcvcvc"+event.getX());
             double width=1215;
             double  LastSecond= ((duration/width)*event.getX());
             plot.setLastSecond(LastSecond);
             mm.setLastSecond(LastSecond);
              double d=event.getX()*16000;
             plot.setEndSample(d);
            plot.setMouseEndpos(d);
          // System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
         //  String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
          // System.out.println(""+input+"\nCopied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
                System.out.println("lastsecond"+LastSecond);
        
                }
        });
         
      
        
        
        
 
     
     
     
       // wavepaneForPaste.setContent(test);
///////
     }
     
     
     
     
     
      //////////////////////////////////////////////multitab/////////////////////////// 
     
     void setpick(String file) throws MalformedURLException{
        System.out.println("bbbb"+file);
         pick = new Media(new File(file).toURI().toURL().toExternalForm());
       // MediaPlayer player = new MediaPlayer(pick);
         trans= new TranslateTransition();
     }
     
     void setfileduration(double duration)
     {
     Duration=duration;
     }
     double getfileduration(){
     return Duration;
     }
     void setmultifilename(String filename){
         multifilename=filename;
     }
     String getmultifilename()
             {
              return multifilename;   
             }
/*     
     
     public class MyResult {
    Media pick;
    MediaPlayer player;
    TranslateTransition  trans;
    
    // etc
}
     public MyResult someMethod() {
    // impl here
}
  */
     Media getpick(){
     return pick;
     }
     /////////////////multitabfor resample////////////////
      
     void testForResample(Stage classStage, double[] samples, double factor,int numsamples,TabPane TP,String filename,double duration)
     {
        //  Pane linepane=new Pane();
        //  linepane.getChildren().clear();
        //  test.getChildren().clear();////here test is a group
       // CheckMenuItem item8 = this.getitem8();
      //  item8.setSelected(true);
        double samples_per_pixcel=numsamples/1215;
        
        Plotwave plot=new Plotwave();
        
        //tab=new Tab(); 
        java.nio.file.Path p=Paths.get(filename);
        String substring= p.getFileName().toString();
        Tab tab=new Tab();
        
        
        
        
        
        
        
        tab.setText(substring); 
        tab.setClosable(true);
        TP.getTabs().addAll(tab);
        
        ScrollPane pane =new ScrollPane();
        
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
       
   
        
        
     //   pane.setPannable(false);
     //   test.setStyle("-fx-background-color:#222222");
       
       // test.setMinSize(1215, 394);
      //  test.setPrefHeight(394);
       // test.setPrefWidth(1215);
       
      //  linepane.getChildren().addAll(test);
        Pane test=new Pane();
    //    Group test=new Group();
        pane.setContent(test);
     //   AnchorPane an=new AnchorPane();
        //an.getChildren().add(pane);
       // ScrollPane pane1 =new ScrollPane();
        
      //  double samples_per_pixcel=1215/2;
        
       // tab.setContent(pane);
        tab.setContent(pane);
      //  TP.getSelectionModel().select(tab);
     /* 
        TP.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    @Override
    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
        // do something...
        System.out.println("vvvbbvbvbvbv"+filename);
        
    }

        });
     */
     /*
       SingleSelectionModel<Tab> selectionModel = TP.getSelectionModel();
     selectionModel.select(tab); //select by object
     selectionModel.select(1); //select by index starting with 0
     selectionModel.clearSelection();//clear your selection 
   
    
        tab.setOnSelectionChanged(new EventHandler<Event>() {
             
            @Override
            public void handle(Event event) {
                event.consume();
              //  TP.selectionModelProperty().set(selectionModel);
                // System.out.println("vvvbbvbvbvbv"+filename);
            
              Media pick;
                try {
                    pick = new Media(new File(filename).toURI().toURL().toExternalForm());
                    MediaPlayer player = new MediaPlayer(pick);
                    TranslateTransition  trans= new TranslateTransition();
                    ///setter method to be created
                    
                    setpick(filename);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                }
         
              // MediaPlayer player =
         // selectionModel.clearSelection();
            }
        });
     */
     
      tab.setOnCloseRequest(new EventHandler<Event>(){  
            @Override
            public void handle(Event event) 
            {
                try 
                {
                    tab_closed=1;
                    alertActionForResample();
                    System.out.println("cvbcvbvbc");
                    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (UnsupportedAudioFileException ex) {
                     Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
          tab.setOnSelectionChanged(event -> {
        if (tab.isSelected()) {
            System.out.println("Tab is Selected"+filename);
            try {
                setpick(filename);
                setfileduration(duration);
                setmultifilename(filename);
            } catch (MalformedURLException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
       
        
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
    
        
     /////vertical gridline///////   
        Line[] lines1=new Line[10];
        int j=120;
         for(int i=0;i<10;i++){
                   
         lines1[i]=new Line(j*i,0,j*i,350);
         
       test.getChildren().addAll(lines1[i]); 
        
   //      pane.setContent(test);
       //  pane1.setContent(test);
          }
        
       /////vertical gridline///////  
         /////waveform display////  
     Line[] lines=new Line[numsamples];      
     for( int i=1;i<numsamples-1;i++){
 
//lines[i]=new Line((i)/samples_per_pixcel,samples[i]*150+197,((i+1))/samples_per_pixcel,samples[i+1]*150+197);////we can set 150 also for magnified amplitude
//lines[i]=new Line((i/samples)*785,samples[i]*225+197,((i+1)/samples)*785,samples[i+1]*225+197);//previous formula
 lines[i]=new Line(i/samples_per_pixcel,samples[i]*100+190,(i+1)/samples_per_pixcel,samples[i+1]*100+190);
 //test.getChildren().add(new Line(120,120,80,90));
 test.getChildren().addAll(lines[i]);
// lines[i].setStroke(Color.GREEN);
// pane.setContent(test); 
 //pane1.setContent(test);
 
     }
        
        

 test.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                plot.setMouseStartpos(event.getX());
                plot.setMousestartYpos(event.getY());
                re.setX(plot.getMouseStartpos());
                re.setY(plot.getMousestartYpos()-394);
                re.setHeight(778+plot.getMousestartYpos());
                re.setWidth(0);
                re.setFill(Color.BLUE);
                re.setOpacity(0.3);
                int width=1215;
                double  StartSecond= ((duration/width)*event.getX());  ////here factor is sam.freq
                plot.setStartSecond(StartSecond);
                double d=event.getX();
                System.out.println("startsecondddddd"+StartSecond);
                plot.setStartSample(d);
                mm.setStartSecond(StartSecond);
          }
        });
 
 test.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                re.setWidth(event.getX()-plot.getMouseStartpos());
                //pane.getChildren().add(re);
               // pane.setContent(re);
          //    linepane.getChildren().add(re);
             //  pane.setContent(test);
               test.getChildren().add(re);
            }
        });
 
test.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            // System.out.println("vcvcvcvc"+event.getX());
             double width=1215;
             double  LastSecond= ((duration/width)*event.getX());
             plot.setLastSecond(LastSecond);
             mm.setLastSecond(LastSecond);
              double d=event.getX()*16000;
             plot.setEndSample(d);
            plot.setMouseEndpos(d);
          // System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
         //  String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
          // System.out.println(""+input+"\nCopied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
                System.out.println("lastsecond"+LastSecond);
        
                }
        });
         
      
        
        
        
 
     
     
     
       // wavepaneForPaste.setContent(test);
///////
     }
     
     
     
     
     
     
     /////////////////multitabfor resample////////////////
      public void tickmenuitem(float sr){
        
    if(sr==8000.0){
        mi8.setSelected(true);
    }
    else if (sr==11025.0){
        mi11.setSelected(true);
    }
    else if (sr==16000.0){
        mi16.setSelected(true);
    }
    else if (sr==22050.0){
        mi22.setSelected(true);
    }
    else if (sr==32000.0){
        mi32.setSelected(true);
    }
    else if (sr==44100.0){
        mi44.setSelected(true);
    }
    else if (sr==48000.0){
        mi48.setSelected(true);
    }
    else if (sr==96000.0){
        mi96.setSelected(true);
    }
    
    
    }
     public void clearcheckmenuitem(){
        
    mi8.setSelected(false);
    mi11.setSelected(false);
    mi16.setSelected(false);
    mi22.setSelected(false);
 //   mi32.setSelected(false);
    mi44.setSelected(false);
//    mi48.setSelected(false);
    mi96.setSelected(false);
     
    }
     
     //////testForZoom/////////////////  
     
    
     void testForZoom(Stage classStage, double[] samples, double factor,int numsamples,TabPane TP,String filename,double duration) {
      //  Pane linepane=new Pane();
      //  linepane.getChildren().clear();
      //  test.getChildren().clear();////here test is a group
        double samples_per_pixcel=numsamples/1215;
        
        plot=new Plotwave();
        
        //tab=new Tab(); 
        java.nio.file.Path p=Paths.get(filename);
        String substring= p.getFileName().toString();
        Tab tab=new Tab();
        tab.setText(substring); 
        tab.setClosable(true);
        TP.getTabs().addAll(tab);
        
        ScrollPane pane =new ScrollPane();
        
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
       
   
        
        
     //   pane.setPannable(false);
     //   test.setStyle("-fx-background-color:#222222");
       
       // test.setMinSize(1215, 394);
      //  test.setPrefHeight(394);
       // test.setPrefWidth(1215);
       
      //  linepane.getChildren().addAll(test);
        Group test=new Group();
    //    Group test=new Group();
        pane.setContent(test);
     //   AnchorPane an=new AnchorPane();
        //an.getChildren().add(pane);
       // ScrollPane pane1 =new ScrollPane();
        
      //  double samples_per_pixcel=1215/2;
        
       // tab.setContent(pane);
        StackPane gp=new StackPane();
        
        Pane pp=new Pane();
        gp.getChildren().addAll(pane,pp);
        tab.setContent(gp);
        TP.getSelectionModel().select(tab);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
    
        
     /////vertical gridline///////   
     Line[] lines1=new Line[10];
        int j=120;
         for(int i=0;i<10;i++){
                   
         lines1[i]=new Line(j*i,0,j*i,350);
         
       test.getChildren().addAll(lines1[i]); 
        
   //      pane.setContent(test);
       //  pane1.setContent(test);
          }
        
       /////vertical gridline///////    
        
        

 gp.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                plot.setMouseStartpos(event.getX());
                plot.setMousestartYpos(event.getY());
                re.setX(plot.getMouseStartpos());
                re.setY(plot.getMousestartYpos()-394);
                re.setHeight(770+plot.getMousestartYpos());
                re.setWidth(0);
                re.setFill(Color.BLUE);
                re.setOpacity(0.3);
                int width=1215;
                double  StartSecond= ((duration/width)*event.getX());  ////here factor is sam.freq
                plot.setStartSecond(StartSecond);
                double d=event.getX();
                System.out.println("startsecondddddd"+StartSecond+"duration"+duration+"width"+width+"event"+event.getX());
                plot.setStartSample(d);
          }
        });
 
 gp.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                re.setWidth(event.getX()-plot.getMouseStartpos());
                //pane.getChildren().add(re);
               // pane.setContent(re);
          //    linepane.getChildren().add(re);
             //  pane.setContent(test);
               pp.getChildren().add(re);
            }
        });
 
gp.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
             System.out.println("vcvcvcvc"+event.getX());
             double width=1215;
             double  LastSecond= ((duration/width)*event.getX());
             plot.setLastSecond(LastSecond);
              double d=event.getX()*16000;
             plot.setEndSample(d);
            plot.setMouseEndpos(d);
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
           System.out.println(""+input+"\nCopied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
           
        
                }
        });
         
      
        
        
        
   /////waveform display////  
     Line[] lines=new Line[numsamples];      
     for( int i=1;i<numsamples;i++){
 
//lines[i]=new Line((i)/samples_per_pixcel,samples[i]*150+197,((i+1))/samples_per_pixcel,samples[i+1]*150+197);////we can set 150 also for magnified amplitude
//lines[i]=new Line((i/samples)*785,samples[i]*225+197,((i+1)/samples)*785,samples[i+1]*225+197);//previous formula
 lines[i]=new Line(i/samples_per_pixcel,samples[i]*120+197,(i+1)/samples_per_pixcel,samples[i+1]*120+197);
 
 test.getChildren().addAll(lines[i]);
// lines[i].setStroke(Color.GREEN);
// pane.setContent(test); 
 //pane1.setContent(test);
 
     }
       // wavepaneForPaste.setContent(test);
///////
     }
     
      
     
     
     
     
     
     
     
     
     
     
     
     
     
////////////////////testforzoom//////////     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    /*
  
     
     
   
     
      void drawLine(Stage classStage, double[] samples, double factor,int numsamples,TabPane TP,String filename,double duration,Pane linepane ) {
        
        plot=new Plotwave();
        lines=new Line[numsamples];
        tab=new Tab();
        java.nio.file.Path p=Paths.get(filename);
        String substring= p.getFileName().toString();
        tab.setText(substring); 
        tab.setClosable(true);
        TP.getTabs().addAll(tab);
        
        pane =new ScrollPane();
        pane.setContent(test);
     //   linepane.getChildren().addAll(test);
       // ScrollPane pane1 =new ScrollPane();
        
      //  double samples_per_pixcel=1215/2;
        
        tab.setContent(pane);
        
        TP.getSelectionModel().select(tab);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
    
        
        
        int j=120;
         for(int i=0;i<10;i++){
                   
         lines1[i]=new Line(j*i,0,j*i,392);
         
        test.getChildren().addAll(lines1[i]); 
        
   //      pane.setContent(test);
       //  pane1.setContent(test);
          }
        
        
        
        
        
        
       
 linepane.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                linepane.getChildren().clear();
                plot.setMouseStartpos(event.getX());
                plot.setMousestartYpos(event.getY());
                re.setX(plot.getMouseStartpos());
                re.setY(plot.getMousestartYpos()-391);
                re.setHeight(394+plot.getMousestartYpos());
                re.setWidth(0);
                re.setFill(Color.BLUE);
                re.setOpacity(0.3);
                int width=1215;
                double  StartSecond= ((duration/width)*event.getX());  ////here factor is sam.freq
                plot.setStartSecond(StartSecond);
          }
        });
 
 linepane.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                re.setWidth(event.getX()-plot.getMouseStartpos());
                //pane.getChildren().add(re);
               // pane.setContent(re);
                linepane.getChildren().add(re);
             //  pane.setContent(test);
               
            }
        });
 
linepane.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
             double width=1190;
             double  LastSecond= ((duration/width)*event.getX());
             plot.setLastSecond(LastSecond);
        
                }
        });
         
      
         
     for( int i=0;i<numsamples;i++){
      lines[i]=new Line((i/factor)*785,samples[i]*225+170,((i+1)/factor)*785,samples[i+1]*225+170);//right line
// lines[i]=new Line((i/factor)*samples_per_pixcel,samples[i]*225+190,((i+1)/factor)*samples_per_pixcel,samples[i+1]*225+190);
 test.getChildren().addAll(lines[i]);
// pane.setContent(test); 
 //pane1.setContent(test);
 
     }
       // wavepaneForPaste.setContent(test);

     }  
 
    
    
    
    */
   
    
    
    
    
    
    
    void zoomin(Float value,double duration,double[] samples,int numsamples){
          Platform.setImplicitExit(false);
       pane.setPannable(false);
       if(value<10)
    {
        
    //    System.out.println("i am here"+value);
  // xAxis.setTickUnit(.05);
    
    //xAxis= new NumberAxis("", 0d, duration, .001);
    
    
    //scrollbar.setVisibleAmount(100);
    //scrollbar.setValue(50); 
  // wavepane.setPrefWidth(2800+value*1000);
   // wavepane.setPrefHeight(wavepane.getHeight()+value*250);
 // wavepane.setMinWidth(wavepane.getWidth()+value*1000);
      //   wavepane.setMaxWidth(wavepane.getWidth()+value*250);
    
  //   wavepane.setMaxHeight(wavepane.getHeight()+value*250);
  //   wavepane.setMinHeight(wavepane.getHeight()+value*250);
  //  scroll.setContent(wavepane);
  //  
 //    wavepane.setSize(2500, 389);
      //  wavepane.getChildren().clear();
        
 //Line[] lines=new Line[numsamples];
//  for( int i=0;i<numsamples;i++){
 ////lines[i]=new Line(((i/2)/factor)*785,samples[(i/2)]*225+170,(((i/2)+1)/factor)*785,samples[(i/2)+1]*225+170);
// wavepane.getChildren().addAll(lines[i]); 
 // }
        
 test.setScaleX(value);
 pane.setContent(test);
// tab.setContent(pane);
// wavepane.setScaleY(value); 
// wavepane.setTranslateX(value/5);
  //wavepane.setTranslateY(value/10);
 for(int i=0;i<numsamples;i++){
  ///////////////////////////////////////////////////////////a different approach-->   lines[i].setScaleX(value);
  lines[i].setStrokeWidth(.3);
 // lines1[i].setStrokeWidth(.2);
 }

   pane.setHmax(50);
 
 
 
 
 
 
 
 
    }
    else if(value>=10&&value<30){ 
  //   wavepane.setMinWidth(1180+value*250);
  //   wavepane.setPrefWidth(wavepane.getWidth()+value*1000);
   test.setScaleX(value);
   pane.setContent(test);
  //  tab.setContent(pane);
 //  wavepane.setScaleY(value);
 //   wavepane.setTranslateX(value/5);
 // wavepane.setTranslateY(value/10);
// scroll.setContent(wavepane);
    pane.setHmax(60);
 
    //xAxis.setTickUnit(value*.1);////////////////set the value
   //  xAxis.setTickUnit(.08);
     
   //  wavepane.setHvalue(.50);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
 //  wavepane.setSize(2500, 389); 
 for(int i=0;i<numsamples;i++){
  lines[i].setStrokeWidth(value/67);
 // lines1[i].setStrokeWidth(.2);
 }
    }
    else if(value>=30&&value<50){ 
 // wavepane.setPrefWidth(wavepane.getWidth()+value*1000);
  test.setScaleX(value);
  pane.setContent(test);
  // tab.setContent(pane);
 // wavepane.setScaleY(value); 
 //wavepane.setTranslateX(value/10);
 // wavepane.setTranslateY(value/10);
  
  pane.setHmax(60);
  
// scroll.setContent(wavepane);
  //wavepane.setMinWidth(0);
    //xAxis.setTickUnit(value*.1);////////////////set the value
   //  xAxis.setTickUnit(.05);
     
     //wavepane.setHvalue(.40);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
  //  wavepane.setSize(2500, 389); 
    for(int i=0;i<numsamples;i++){
  lines[i].setStrokeWidth(value/134);
 // lines1[i].setStrokeWidth(.2);
 }
    }
    else if(value>=50&&value<70){ 
 // wavepane.setPrefWidth(wavepane.getWidth()+value*1000);
 test.setScaleX(value);
 pane.setContent(test);
//  tab.setContent(pane);
 //  wavepane.setScaleY(value); 
 //wavepane.setTranslateX(value/15);
 // wavepane.setTranslateY(value/10);
  pane.setHmax(70);
   
 //scroll.setContent(wavepane);
 // wavepane.setSize(2500, 389);
  //scroll.setContent(wavepane);
    //xAxis.setTickUnit(value*.1);////////////////set the value
   //  xAxis.setTickUnit(.03);
     
  //   wavepane.setHvalue(.30);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
   for(int i=0;i<numsamples;i++){ 
  lines[i].setStrokeWidth(value/200);
 // lines1[i].setStrokeWidth(.2);
 }
    }
    else if(value>=70&&value<90){  
 //  wavepane.setPrefWidth(wavepane.getWidth()+value*1000);
  test.setScaleX(value);
  pane.setContent(test);
//   tab.setContent(pane);
 //  wavepane.setScaleY(value); 
// wavepane.setTranslateX(value/20);
//  wavepane.setTranslateY(value/10);
   pane.setHmax(50);
   
 //scroll.setContent(wavepane);
    //xAxis.setTickUnit(value*.1);////////////////set the value
   //  xAxis.setTickUnit(.02);
     
    // wavepane.setHvalue(.20);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
//     wavepane.setSize(2500, 389);
  for(int i=0;i<numsamples;i++){
  lines[i].setStrokeWidth(value/400);
 // lines1[i].setStrokeWidth(.2);
 } }
    else if(value>90)
            {
  test.setScaleX(value);
  pane.setContent(test);
 //  tab.setContent(pane);
//  wavepane.setScaleY(value); 
  //wavepane.setTranslateX(value/10);
 // wavepane.setTranslateY(value/10);
 // scroll.setContent(wavepane);
 // scroll.autosize();
  //xAxis.setTickUnit(value*.1);////////////////set the value
   //  xAxis.setTickUnit(.01);
     
   //  wavepane.setHvalue(.10);
 //   wavepane.setSize(2500, 389);
for(int i=0;i<numsamples;i++){
  lines[i].setStrokeWidth(value/1500);
//  lines[i].setSmooth(true);
 // lines1[i].setStrokeWidth(.2);
 }  }
        
        
       
    }
    
    
    
    
    
      double[] pixcelconversion(double[] samples,int numsamples){
        
          double result[] = new double[numsamples];
          for(int i=0;i<numsamples;i++){
          result[i]=((samples[i]+1)/2)*406;
          }
          return result;
          
          
      }

    void drawLine1(Stage classStage, double[] samples, double factor, int numsamples, TabPane TP, String filename, double duration, Pane linepane, int check) {
  
        
        linepane.getChildren().clear();
        test.getChildren().clear();
        double samples_per_pixcel=numsamples/1215;
        
        plot=new Plotwave();
        
        //tab=new Tab(); 
        java.nio.file.Path p=Paths.get(filename);
        String substring= p.getFileName().toString();
        tab.setText(substring); 
        tab.setClosable(true);
        TP.getTabs().addAll(tab);
        
        pane =new ScrollPane();
        
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
       
   
        
        
     //   pane.setPannable(false);
     //   test.setStyle("-fx-background-color:#222222");
       
       // test.setMinSize(1215, 394);
      //  test.setPrefHeight(394);
       // test.setPrefWidth(1215);
       
        linepane.getChildren().addAll(test);
        
        pane.setContent(linepane);
         
       // ScrollPane pane1 =new ScrollPane();
        
      //  double samples_per_pixcel=1215/2;
        
        tab.setContent(pane);
        
        TP.getSelectionModel().select(tab);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
    
        
     /////vertical gridline///////   
        int j=120;
         for(int i=0;i<10;i++){
                   
         lines1[i]=new Line(j*i,0,j*i,394);
         
       linepane.getChildren().addAll(lines1[i]); 
        
   //      pane.setContent(test);
       //  pane1.setContent(test);
          }
        
       /////vertical gridline///////    
        
        
        
        
       
 test.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                plot.setMouseStartpos(event.getX());
                plot.setMousestartYpos(event.getY());
                re.setX(plot.getMouseStartpos());
                re.setY(plot.getMousestartYpos()-394);
                re.setHeight(778+plot.getMousestartYpos());
                re.setWidth(0);
                re.setFill(Color.BLUE);
                re.setOpacity(0.3);
                int width=1215;
                double  StartSecond= ((duration/width)*event.getX());  ////here factor is sam.freq
                plot.setStartSecond(StartSecond);
          }
        });
 
 test.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                re.setWidth(event.getX()-plot.getMouseStartpos());
                //pane.getChildren().add(re);
               // pane.setContent(re);
              linepane.getChildren().add(re);
             //  pane.setContent(test);
               
            }
        });
 
test.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
             double width=1215;
             double  LastSecond= ((duration/width)*event.getX());
             plot.setLastSecond(LastSecond);
        
                }
        });
         
      
        
        
        
   /////waveform display////  
     lines=new Line[numsamples];   
     //relines=new Line[numsamples];
     for( int i=1;i<numsamples;i++){
 
//lines[i]=new Line((i)/samples_per_pixcel,samples[i]*150+197,((i+1))/samples_per_pixcel,samples[i+1]*150+197);////we can set 150 also for magnified amplitude
//lines[i]=new Line((i/samples)*785,samples[i]*225+197,((i+1)/samples)*785,samples[i+1]*225+197);//previous formula
 lines[i]=new Line(i/samples_per_pixcel,samples[i]*150+197,(i+1)/samples_per_pixcel,samples[i+1]*150+197);
// relines[i]=new Line(i/samples_per_pixcel,samples[i]*100+250,(i+1)/samples_per_pixcel,samples[i+1]*100+250);
 test.getChildren().addAll(lines[i]);
// lines[i].setStroke(Color.GREEN);
// pane.setContent(test); 
 //pane1.setContent(test);
 
     }
    
    
    
      /////////////resample modify from here///////////////////////// 

      /*
         Tab tab=new Tab(); 
         TP.getTabs().addAll(tab);
         ScrollPane pane =new ScrollPane();
        
        //  pane.setFitToWidth(true);
        //  pane.setFitToHeight(true);
          pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
          pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
          VBox vb=new VBox();
          Pane newpane=new Pane();
          linepane.getChildren().addAll(newpane);
        
          pane.setContent(linepane);
         
       // ScrollPane pane1 =new ScrollPane();
        
      //  double samples_per_pixcel=1215/2;
        
           tab.setContent(pane);
        
           TP.getSelectionModel().select(tab);
           TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
      
      
      
      
      
      */
      
      




       }
    ////resample draw///////
   void drawResample(Stage classStage, double[] samples, double factor, int numsamples, TabPane TP, String filename, double duration, Pane linepane, int check) {
        double samples_per_pixcel=numsamples/1215;
        plot=new Plotwave();
        java.nio.file.Path p=Paths.get(filename);
        String substring= p.getFileName().toString();
        Tab tab =new Tab();
        tab.setText(substring); 
        tab.setClosable(true);
        TP.getTabs().addAll(tab);
        TP.getSelectionModel().select(tab);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        tab.setOnCloseRequest(new EventHandler<Event>(){  
            @Override
            public void handle(Event event) {
                try {
                    tab_closed=1;
                    alertActionForResample();
                    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (UnsupportedAudioFileException ex) {
                     Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        ScrollPane pane =new ScrollPane();
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        
        Pane gp=new Pane();
        pane.setContent(gp);
        tab.setContent(pane);
        /////vertical gridline///////   
         int j=120;
         Line[] lines1=new Line[10];
         for(int i=0;i<10;i++){
         lines1[i]=new Line(j*i,0,j*i,394);
         gp.getChildren().addAll(lines1[i]); 
         }
        
       /////vertical gridline///////    
         ///waveform///
       Line[] lines=new Line[numsamples];   
        for( int i=1;i<numsamples;i++){
        lines[i]=new Line(i/samples_per_pixcel,samples[i]*150+197,(i+1)/samples_per_pixcel,samples[i+1]*150+197);
       gp.getChildren().addAll(lines[i]);
         }
        ///waveform////
       gp.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println("bcjkbsdjkcvbjksdbjkls");
                plot.setMouseStartpos(event.getX());
                plot.setMousestartYpos(event.getY());
                re.setX(plot.getMouseStartpos());
                re.setY(plot.getMousestartYpos()-394);
                re.setHeight(778+plot.getMousestartYpos());
                re.setWidth(0);
                re.setFill(Color.BLUE);
                re.setOpacity(0.3);
                int width=1215;
                double  StartSecond= ((duration/width)*event.getX());  ////here factor is sam.freq
                plot.setStartSecond(StartSecond);
          }
        });
 
      gp.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println("bcjkbsdjkcvbjksdbjkls");
                re.setWidth(event.getX()-plot.getMouseStartpos());
                //pane.getChildren().add(re);
               // pane.setContent(re);
              linepane.getChildren().add(re);
             //  pane.setContent(test);
               
            }
        });
 
      gp.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.out.println("bcjkbsdjkcvbjksdbjkls");
             double width=1215;
             double  LastSecond= ((duration/width)*event.getX());
             plot.setLastSecond(LastSecond);
        
                }
        });
    
   }
    ///////resample draw////
    
   ///try resample draw////
  
   
   
   
   
   
   
   
   
   
  /// end of try resample/// 
   
//////////////////Articulation error////////////
 String Articulate_error(String filename, String error) throws IOException, InterruptedException {

        String valuescan="",valuescan1="",valuescan2="";
        
                         double startpos=mm.getStartSecond()*16000;
                         String startposition=String.valueOf(startpos);
                         System.out.println("artiString"+startpos+"artiDouble"+startpos);
                        String currentDir = System.getProperty("user.dir");
                        String cexedir = currentDir + "\\cexe\\";
                        System.out.println("correct format"+cexedir);
                        System.out.println("String error"+error);
                      try {
                          Process p1;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_removing_filewriting",
                                    filename,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc_output_13dim.txt",error,startposition
                                    
                            );///update it as   error,startposition
                          
                          
                           p1 = pb1.start();//
                         // p1.wait(1000);
                          System.out.println("fdgdfhdfhdfhdfhfdhdf"); 
                         //  int waitForValue = p1.waitFor();
                              
                   //    BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
                          Scanner sc=new Scanner(p1.getInputStream());  
                  //         float valuescann=Float.valueOf(sc.nextLine());
                 //           String  value=br.readLine();
                         valuescan=sc.next();
                         valuescan1=sc.nextLine().trim();
                         valuescan2=valuescan+valuescan1;
                         setMahaDistance(valuescan);
                         seterroe(valuescan1);
                         System.out.println("value----->\n"+valuescan);   
                //       System.out.println("value----->\n"+br.readLine());
                //       System.out.println("value----->\n"+br.read());
                         System.out.println("value----->\n"+valuescan1);
                         System.out.println("value concat----->\n"+valuescan2);
                    //    System.out.println("value new----->\n"+value);
                        //  System.out.println("value new1-->\n"+br.read());
                   //    System.out.println("value\t"+sc.next());
                                    
                     //   int waitForValue = p1.waitFor();
                       //       p1.isAlive();// try {
                                      //System.out.println("i am here");
                                     // Process p = pb.start();
                                      /*try {
                                      pb.wait(0);
                                      } catch (InterruptedException ex) {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                      */
                              // s p.waitFor();
                            //  int waitFor = p.waitFor();
                            //  p.isAlive();
                            //          System.out.println("wait for value"+waitForValue);
                                      // Scanner sc=new Scanner(p1.getInputStream());
                                    // p1.waitFor();
                                      // valuefromArticulate= sc.nextFloat();
                                  //  String value=sc.next();
                                   // System.out.println("value"+value);
                    
                      }              
                      catch(Exception e){}
                     // Thread.sleep(1000);
      String f= currentDir+"\\2.txt";
      File file=new File(f);
      file.delete();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  return valuescan2;  
  }
 private void setMahaDistance(String valuescan) {
        mahadistance=valuescan;
          //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void seterroe(String valuescan1) {
        error=valuescan1; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
 public String getmahadistance(){
    return mahadistance;
    }
    public String geterror(){
    return error;
    }   
    
    
    
    
    
    


/////////////////////////////////////////////////
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
      
   
   
   
   
   


      
     
   
      
      
    