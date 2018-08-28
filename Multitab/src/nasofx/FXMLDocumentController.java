/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

//import java.awt.event.MouseEvent;
import java.awt.Canvas;
import java.awt.Desktop;
import static java.awt.SystemColor.window;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
//import javazoom.jl.decoder.JavaLayerException;
/**
 *
 * @author IITG
 */
public class FXMLDocumentController extends Application {
      @FXML
    private AnchorPane tab1ap,BasicPane;
      @FXML
    private LineChart<?, ?> lineChartForRecord;
    
    @FXML
    private Label recordlabel;

    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem pasteitem;
    @FXML
    private Slider slider;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tabForPaste; 
    @FXML
    private Tab tabForPaste1; 
    @FXML
    private TabPane TP;
    @FXML
    private ScrollPane wavepane;
    @FXML
    private ScrollPane wavepaneForPaste;  
    @FXML
    private ScrollPane wavepaneForPaste1;     
 
    @FXML
    private AnchorPane tab1ap1;
    
    @FXML
    private MenuItem hypernasality;
    @FXML
    private MenuItem scorecard;
    
    @FXML
    private MenuItem saveas;
     @FXML
    public ImageView marker;
     
     @FXML
    private AnchorPane counterap;
     @FXML
    private Label hour;

    @FXML
    private Label min;

    @FXML
    private Label sec;

    @FXML
    private Label milisec;
    @FXML
    private MenuItem closebtn;   
      @FXML
    private Button stopicon;
     
      
    @FXML
    private AnchorPane resultpane;
       @FXML
    private Button recordbtn1;
      
    @FXML
    private CheckMenuItem mi8k;

    @FXML
    private CheckMenuItem mi11k;

    @FXML
    private CheckMenuItem mi16k;

    @FXML
    private CheckMenuItem mi22k;

    @FXML
    private CheckMenuItem mi32k;

    @FXML
    private CheckMenuItem mi44k;

    @FXML
    private CheckMenuItem mi48k;

    @FXML
    private CheckMenuItem mi96k;


      
   //  public  Media pick;  
    // public Media[] pick;
       public MediaPlayer player ;
      //public  TranslateTransition trans;
       
       @FXML
    private MenuItem open_menu_item;
         @FXML
    private Button fwdbtn;
         
    @FXML
    private Button rewindbtn;
    @FXML
    private Button stopbtn;
    @FXML
    private Button playbtn;
    
    
    @FXML
    private Button recordbtn;
     @FXML
    private MenuItem selectall;

     
    // @FXML
  //  private Pane LinePane;
  
    final Tooltip playtip = new Tooltip();
    final Tooltip pausetip = new Tooltip();
    final Tooltip forwardtip = new Tooltip();
    final Tooltip rewindtip = new Tooltip();
    final Tooltip stoptip = new Tooltip();
    final Tooltip recordtip = new Tooltip();
    AudioInputStream audioInputStream;
    
    
     
     double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
     @FXML
     final int bufSize = 16384;
    public  double frames_per_pixel;
    
    static int count=0;
    
    String errStr;
    double duration, seconds;
    File file;
    public String fileName = "untitled";
    public String abbfilePath = null;
    Vector lines = new Vector();
    private Toolkit tk;
    public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
    public JPopupMenu menu;
    public StreamBytes streamBytes;
  //  public MainFrame mainFrame;
   // public Mainpopup mainpopup;
//    private SamplingGraph sg;
    private int graphFromScreen = 5, graphVerticalSize = 240, normalPixcel = 60;
    public boolean selectedPlay = false;
  //  public StreamVariables streamVariable;
 //   public RightClickEvent rightClick;
    public String[][] annotationPos;
    public ByteArrayOutputStream capOut;
    private int samplingpanelSize;
    List<List<Integer>> dummyList;
  //  public ServerFinder serverStatus;
    public int xSize;
    public String fileHashValue;
    private double record_duration;
    private boolean buffStatus = true;
    private boolean lineStatus = false;
    public static String filenamefortab;
    public static Stage classStage = new Stage();
    public static Stage classStage1 = new Stage();
    
    private Thread thread;
    private AudioFormat format,format1;  
    private int[] audioDataNormalize;
    private int normalizedValue = 3000;
    public SignalProc sigProc;
    public static String dummy;
    Capture cap = new Capture();
     TranslateTransition trans1= new TranslateTransition();
     TranslateTransition trans2= new TranslateTransition();
 // public  LineChart lineChart ;
      boolean variable=false;
      XYChart.Data<String,Double> dd;
      double[] samples;
      boolean recordstop=false;
    @FXML
    private LineChart wave;
    
    @FXML
    private NumberAxis recordxaxis;
    @FXML
    private LineChart<? ,? > recordinglinechart;
    Thread recordplot;
    NasoFX nfx=new NasoFX();
    double factor;
    int numSamples;
    float frameRate;
    int frameSize;
    int actual_frames_per_pixel;
    Object source ;
    ByteArrayOutputStream capOut1;
      @FXML
//    private ImageView marker1;
    Stage getstage(){
      return classStage;
      }
    ///////////////////////////
    Button getrecordbtn(){
    return recordbtn;
    }  
    
     public static int NumSamples=0;  
     public double[] fpoints;
    //////////////////////////////////
      @FXML
      void shutdown(){
          System.out.println("hjkgvkhgvkhgkhlhl");
      }
      
   @FXML
    void Zoomfunction(MouseEvent event) {
        
        float value = (float) slider.getValue();
     //   System.out.println("value slider"+value);
       // nfx.dozoom(value,wavepane,duration);
        
       nfx.zoomin(value,duration,fpoints,NumSamples);
        
        
        

    }

    
    @FXML
    void scorecardclick(ActionEvent event) {
        Tab tab1= new Tab();
            tab1.setText("Scorecard  ");
            TP.getTabs().add(tab1);
            TP.getSelectionModel().select(tab1);

    }
    
    
    @FXML
    void markerpress(MouseEvent event) {
        orgSceneX = event.getSceneX();//event.getX();
            //orgSceneY = event.getY();
            orgTranslateX = ((ImageView)(event.getSource())).getTranslateX();
          //  orgTranslateY = ((ImageView)(event.getSource())).getTranslateY();
    }
    
    
    @FXML
    void markerdrag(MouseEvent event) {
     
        frames_per_pixel=nfx.getfileduration();
        
        
         double offsetX = event.getSceneX()- orgSceneX;
        
           // double offsetY = event.getY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
           // double newTranslateY = orgTranslateY + offsetY;
          // System.out.println("\nThe offset is :"+ newTranslateX);
           
           if(newTranslateX>0.0 && newTranslateX<1170){
             
            ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
            //((ImageView)(event.getSource())).setTranslateY(newTranslateY);
           }
           hour.setText("00");
          min.setText("00");
          double width=1215;
          double factor=(frames_per_pixel*1000)/width;
          double movefactor=newTranslateX*factor;
          int milli=(int)movefactor%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)movefactor/1000;
          String ss=String.valueOf(second);
          
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
          
          
          if(milli>=0 && movefactor < (frames_per_pixel*1000))
          {
          milisec.setText(mm);
          sec.setText(ss);
          
          }
           
            
    }
    
   
    @FXML
    void closesystem(ActionEvent event) {
        System.exit(0);

    }
    
    
     
  //  @Override
    public void initialize() 
    {
        nfx.setmenuitem8(mi8k);
        nfx.setmenuitem11(mi11k);
        nfx.setmenuitem16(mi16k);
        nfx.setmenuitem22(mi22k);
        nfx.setmenuitem32(mi32k);
        nfx.setmenuitem44(mi44k);
      //  nfx.setmenuitem44(mi8k);
        nfx.setmenuitem48(mi48k);
        nfx.setmenuitem96(mi96k);
       // BasicPane.setStyle("-fx-background-color:#222222;");
        // TODO
     /* 
    slider.setMin(0);
    slider.setMax(100);
    slider.setValue(40);
    //slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(50);
    slider.setMinorTickCount(5);
    slider.setBlockIncrement(10);
    
    
   TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
   double a = 0.1;
   int i=0;
  
   
for (i = 3,a=0; i <2100; i+=50,a+=1)
{
    //double a = 0.1;
   String number = Double.toString(a);
    Text t = new Text(i, 23,number);
     double strokeWidth = t.getStrokeWidth();
      System.out.println("the stroke width is  :  "+strokeWidth);
    t.setStrokeWidth(0.1);
    t.setStyle("-fx-text-fill:white; -fx-font-size:10;");
       
      
        t.setStrokeWidth(0.5);
        
       t.setStrokeWidth(0.1);
     t.setStroke(Color.rgb(204, 204, 204));
  
        System.out.println("the new  stroke width is  :  "+strokeWidth);
   
    
    
    
    Line line1 = new Line(i, 20, i, 322);
     line1.setStrokeWidth(0.1);
     
    line1.setStroke(Color.rgb(204, 204, 204));
    
   
    Line line2 = new Line(0, i, 600, i);
    line2.setStroke(Color.LIGHTGRAY); 

    tab1ap.getChildren().addAll(line1,t);
    
}

        Line redLine = new Line(0, 5, 2100, 5);

    redLine.setStroke(Color.rgb(221, 221, 221));
    redLine.setStrokeWidth(10);
    
    tab1ap.getChildren().addAll(redLine);
    
  // marker.toFront();

//counterap.setStyle("-fx-border-color: black");
       
 //nfx.setTab(tab1);
 
  TP.getTabs().addAll(tab1);  
        */
    //    resultpane.setTranslateX(351);
        //wavepane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
  //  wavepane.setHvalue(10);

    }    

    /**
     *
     */
    
    
    
    @FXML
    
    public void open_button_Event(ActionEvent event) throws Exception
    { 
        //Tab tab1=new Tab();
        //TP.getTabs().add(tab1);
         
      //    String filename="";
      //    filename=plot.fileopenmethod();//fileopenmethod();
      //  String SampleRateConversion = this.SampleRateConversion(new File(filename), 8000);
       // System.out.println("filename_in 1st time load-------->>>>>>\t"+filename);
       
       
       ////////multiple file chooser/////////////
       
        final FileChooser fileChooser = new FileChooser();
        List<File> list =fileChooser.showOpenMultipleDialog(classStage);
                    if (list != null) {
                        for (File file : list) {
                           // openFile(file);
                            Plotwave plot=new Plotwave();
                            System.out.println("filenames are:\t"+file.getAbsolutePath());
                             double[] samples = plot.readWaveData(file);
                           //  sendfilename(file.getAbsolutePath());
                            // nfx.sendfilenameinplot(file.getAbsolutePath());
                              AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
           AudioFormat audioFormat = audioInputStream.getFormat();
         //                   System.out.println("audioformat"+audioFormat);
           float sampleRate = audioFormat.getSampleRate();
        //                    System.out.println("sampleRate"+sampleRate);
          // System.out.println("The sample rate is : "+sampleRate);
          // clearcheckmenuitem();
          // tickmenuitem(sampleRate);
         //   pick=new Media[(int)file.length()];
          //   pick = new Media(file.toURI().toURL().toExternalForm());
       //   player = new MediaPlayer(pick);
         // trans= new TranslateTransition();
          
          
          
          
          
           double duration=plot.getduration();
                          //  System.out.println("duration"+duration);
           double factor=plot.getsampfrq();
                        //    System.out.println("factor"+factor);
            frames_per_pixel=plot.getduration();
                        ///    System.out.println("frames_per_pixel"+frames_per_pixel);
          int numsamples=samples.length;
                        //    System.out.println("numsamples"+numsamples);
             int NumSamples=samples.length;
                            
   //end of tickmenu function
         //    String mama= plot.getfilename();
                   int  Checknumsamples=samples.length;               ////(600000-1000000).01   (1000000-3000000).1 .15 .2 .25 .3 .35 .4 .45 .6 .65 .7  
                    
          nfx.test(classStage, samples, factor, NumSamples, TP, file.getAbsolutePath(), duration);
                        }
                    }
       
       
       
       
       
       
       
       
       
       
       //////////multiple filechooser//////////////
       /*
          pick = new Media(new File(filename).toURI().toURL().toExternalForm());
          player = new MediaPlayer(pick);
          trans= new TranslateTransition();
//        transtab=new TranslateTransition();
          double[] samples = plot.readWaveData(filename );
          sendfilename(filename);
          nfx.sendfilenameinplot(filename);
      //  plot.sendfilenameinplot(filename);
  //  RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.1);
  // Function to auto-check the sample rate and tick the menuitem of samplerate
           this.audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
           AudioFormat audioFormat = audioInputStream.getFormat();
           float sampleRate = audioFormat.getSampleRate();
           System.out.println("The sample rate is : "+sampleRate);
           clearcheckmenuitem();
           tickmenuitem(sampleRate);
            duration=plot.getduration();
        factor=plot.getsampfrq();
        frames_per_pixel=plot.getduration();
          int numsamples=samples.length;
       NumSamples=samples.length;
   //end of tickmenu function
         //    String mama= plot.getfilename();
                   int  Checknumsamples=samples.length;               ////(600000-1000000).01   (1000000-3000000).1 .15 .2 .25 .3 .35 .4 .45 .6 .65 .7  
                    
        
                    if( Checknumsamples>600000 && Checknumsamples<1000000)
                    {
                        
                       
                    RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.01);        /////0.01
                    fpoints= rdpf.filter(samples);
                    NumSamples=fpoints.length;
//                    nfx.drawLine(classStage,fpoints,factor,NumSamples,TP,filename,duration,LinePane);
                    }
                    else if(Checknumsamples>1000000 && Checknumsamples<3000000){
                    RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.1);        /////0.1
                    fpoints= rdpf.filter(samples);
                    NumSamples=fpoints.length;
   //                 nfx.drawLine(classStage,fpoints,factor,NumSamples,TP,filename,duration,LinePane);
                      
                    }
                    else if(Checknumsamples>3000000 && Checknumsamples<6000000)
                    {   
                        RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.15);      /////0.03
                        fpoints= rdpf.filter(samples);
                        NumSamples=fpoints.length;
   //                     nfx.drawLine(classStage,fpoints,factor,NumSamples,TP,filename,duration,LinePane);
                
                    }
                    else if(Checknumsamples>6000000){
                        RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.55);      /////0.03
                        fpoints= rdpf.filter(samples);
                        NumSamples=fpoints.length;
    //                    nfx.drawLine(classStage,fpoints,factor,NumSamples,TP,filename,duration,LinePane);
                
                    }
                    {   
     //                   nfx.drawLine(classStage,samples,factor,numsamples,TP,filename,duration,LinePane);
                         nfx.testForZoom(classStage,samples,factor,numsamples,TP,filename,duration);
                    
                     }
                
        
      */  
        
        
        //numSamples=plot.getnumsamples();
                   //  double[] fpoints;
                   // fpoints= rdpf.filter(samples);
                   //  int numsamples=fpoints.length;
                 
        
        //double duration=numsamples/ factor;
      
       // nfx.tempplot(classStage,filename,samples, numsamples, tabForPaste, TP, wavepaneForPaste, factor, duration);
       
   //     SamplingGraph samp=new SamplingGraph();
        
       
    }
 /////////////////////conversion/////////////
     class SamplingGraph extends JPanel implements Runnable {

        @Override
        public void run() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
   
   @FXML
    public String SampleRateConversionForArti(File SourceFile, float dSampleRate) throws UnsupportedAudioFileException, IOException
    {File targetFile=null;
       
        AudioInputStream SourceStream = null;
         float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("Articulateconverted.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
    return targetFile.getAbsolutePath();
    }
     
     
  @FXML
    public String SampleRateConversion(File SourceFile, float dSampleRate) throws UnsupportedAudioFileException, IOException
            
    {    File targetFile=null;
       
        AudioInputStream SourceStream = null;
       
                
        
        
        //OutputStream targetFile = null;
        if(nfx.sr8==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr8.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        if(nfx.sr11==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr11.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        if(nfx.sr16==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr16.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        if(nfx.sr22==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr22.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        if(nfx.sr32==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr32.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        if(nfx.sr44==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr44.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        if(nfx.sr48==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr48.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        if(nfx.sr96==1){
             float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        targetFile = new File("convertedsr96.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        
        }
        
        return targetFile.getAbsolutePath();
    }
   

   
    
  
    
    @FXML
    void SR8(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
    
    mi8k.setSelected(true);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
    String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr8=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
    }
    @FXML
    void SR11(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        
    mi8k.setSelected(false);
    mi11k.setSelected(true);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
      String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr11=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
      

    }
    @FXML
    void SR16(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
    mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(true);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
      String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr16=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
   }
    @FXML
    void SR22(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(true);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
    String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr22=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
  
    }
    @FXML
    void SR32(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(true);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
     String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr32=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
    
    }
    @FXML
    void SR44(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(true);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
     String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr44=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
       

    }
    @FXML
    void SR48(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(true);
    mi96k.setSelected(false);
    String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr48=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
      
    }
    
    @FXML
    void SR96(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
    mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(true);
    String  filename = nfx.getmultifilename();
    File sendfile = new File(filename);
    String newFilename = null;
    nfx.sr96=1;
    newFilename = SampleRateConversion(sendfile, (float) 8000.0);
    Plotwave plot=new Plotwave();
    double[] samples = plot.readWaveData(new File(newFilename));
    duration=plot.getduration();
    factor=plot.getsampfrq();
    numSamples=plot.getnumsamples();
    nfx.Is_resample=1;
    nfx.testForResample(classStage, samples, factor, numSamples, TP, filename, duration);
       
   
  
    }

    @FXML
    public void clearcheckmenuitem(){
        
    mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
     
    }

   
    @FXML
    public void tickmenuitem(float sr){
        
    if(sr==8000.0){
        mi8k.setSelected(true);
    }
    else if (sr==11025.0){
        mi11k.setSelected(true);
    }
    else if (sr==16000.0){
        mi16k.setSelected(true);
    }
    else if (sr==22050.0){
        mi22k.setSelected(true);
    }
    else if (sr==32000.0){
        mi32k.setSelected(true);
    }
    else if (sr==44100.0){
        mi44k.setSelected(true);
    }
    else if (sr==48000.0){
        mi48k.setSelected(true);
    }
    else if (sr==96000.0){
        mi96k.setSelected(true);
    }
    
    
    }
    
      
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendfilename(String filename) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this.dummy=filename;
        }
    String getfilename(){
        return dummy;
    
    }

    private String timeConversion2(double time1) {
        
        String hD = "", mD = "", sD = "", msd="", totalTD = "";
        try {
            int time=(int)time1;
            int hour = time / (1000*3600);
            int hour_balance = time % (1000*3600);
            int min = hour_balance / (60*1000);
            int min_balance = hour_balance % (60*1000);
            int sec=min_balance/(1000);
            int sec_balance=min_balance% (1000);
           // System.out.println("time\t"+time+"sec\t"+sec+"hour"+hour_balance+"minute"+min);
            if (hour < 10*1000) {
                hD = "0" + hour;
            } else {
                hD = hD + hour;
            }
            if (min < 10*1000) {
                mD = "0" + min;
            } else {
                mD = mD + min;
            }
            if (sec < 60*1000) {
                sD = "0" + sec;
            } else {
                sD = sD + sec;
            }
            if(sec_balance<10){
               msd= "00"+sec_balance;          
             }else{
            msd=msd+sec_balance;
            }

            totalTD = hD + ":" + mD + ":" + sD+":"+msd;
           // System.out.println("timeformat"+totalTD);
        } catch (Exception er) {
          //  Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, er);
        }
        return totalTD;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 @FXML
    void hideresult(ActionEvent event) {
          TranslateTransition openNav=new TranslateTransition(new Duration(350), resultpane);
           openNav.setToX(351);
           openNav.play();
        
    }
  ///////////////////////////////select and check hypernasality///////
    
    
   @FXML
    void Select_Check_hypernalasityclick(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

         
        String Select_Check_hypernalasityclick = nfx.Select_Check_hypernalasityclick(nfx.getmultifilename());
          
            double probability = nfx.Hypernasality(Select_Check_hypernalasityclick,resultpane,classStage);
        }
   
    
    
    ////////////////////////end of select check/////////////
   
 @FXML
    void hypernalasityclick(ActionEvent event) {
          final SwingNode swingNode = new SwingNode();
          createAndSetSwingContent(swingNode);
        //  resultpane.getChildren().add((swingNode));
      //    TranslateTransition openNav=new TranslateTransition(new Duration(350), resultpane);
      //     openNav.setToX(0);
      //     openNav.play();
         // Tab tab = new Tab();
      //    tab.setText("Hypernasality  ");
           String filename = nfx.getmultifilename();//this.getfilename(); //fileName);
           
      //      TP.getTabs().add(tab);
       //     TP.getSelectionModel().select(tab);
      //      TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            double probability = nfx.Hypernasality(filename,resultpane,classStage);
          
     System.out.println("The probability value is:"+probability);
     
   /*  
    Rectangle rect = new Rectangle(40,100,100 ,40);
    rect.setX(50);
    rect.setY(50);
    
    rect.setFill(Color.rgb(0, 156, 73));

    rect.setStroke(Color.BLACK);
    
    Text t = new Text();
    String value = Double.toString(probability);
    t.setText("The Hypernasality Score is :"+value);
    t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.RED);
   
    tab.setContent(t);
   */ 
    
  //  classStage.setScene(new Scene(resultpane));
    //classStage.show();
    }
  private void createAndSetSwingContent(final SwingNode swingNode) {
          JFrame window = new JFrame(); 
           
        //  window.add(DC);
      SwingUtilities.invokeLater(new Runnable() {
                  @Override
                 public void run() {
                   //  swingNode.setContent((JComponent) window.getContentPane());
                  // drawingComponent DC = new drawingComponent();
                  // swingNode.setContent(DC);
                 
                 }
             });
         }
    
    
    
    
    
    
    
 
    @FXML
    void playsound(ActionEvent event) throws FileNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        
        
          /////getplayer;gettrans;getpick methods to be created.
          Media pick = nfx.getpick();
          player = new MediaPlayer(pick);
       // TranslateTransition  trans= new TranslateTransition();
     //  String filename = this.getfilename();
    //    System.out.println("filename in playsound"+filename);
        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\Icons\\";
                       // System.out.println("correct format"+cexedir);
            String image1="file:\\"+cexedir+"pausebtn.png";
            String image2="file:\\"+cexedir+"play-arrow-(1).png";
  //  Image pause=new Image(image1);
            Image pause = new Image(image1,27,27,false,false);
      //  Image pause = new Image("C:\\Users\\Naso\\Documents\\NetBeansProjects\\NasoDemoWindows\\Icons\\pausebtn.png",27,27,false,false);
      
        // Image pause=new Image("image1",27,27,false,false);
            Image playimage = new Image(image2,15,17,false,false);
     //  Image playimage=new Image(image2);
            MediaPlayer.Status status =
                    player.getStatus();
        
            double d = pick.getDuration().toMillis();
          // System.out.println("The duration of the file is:"+d);
          // double aaa =Double.parseDouble(d);
          // double translate = 1147.77/d;
            //System.out.println("The translate of the file is:"+translate);   
           // TranslateTransition trans= new TranslateTransition();
           
          
            nfx.trans.setDuration(Duration.millis(d));
            nfx.trans.setToX(1180);
            nfx.trans.setNode(marker);
     //       transtab.setDuration(Duration.millis(d));
         //   transtab.setToX(1215);
         //   transtab.setNode(marker1);
            
          // Animation.Status  tttt= trans.getStatus();
           //System.out.println("The animation status is :"+tttt);
           if(!(status == MediaPlayer.Status.PLAYING) && !(status == MediaPlayer.Status.PAUSED)) {
                player.play();
               
                player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
       
      double a = nfx.trans.getCurrentTime().toMillis();
      int aa = (int)a;
      
       int milli=(int)aa%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)aa/1000;
          String ss=String.valueOf(second);
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
          
              
          milisec.setText(mm);
          sec.setText(ss);
          
           
   }
        });
                nfx.trans.play();
            //    transtab.play();
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
                playbtn.setGraphic(new ImageView(pause));
                player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                       playtip.setText("play");
                       playbtn.setTooltip(playtip);
                       playbtn.setGraphic(new ImageView(playimage));
                       
                } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
        
         }
            
         else if (status == MediaPlayer.Status.PLAYING )
         {
         player.pause();
         nfx.trans.pause();
       //  transtab.pause();
         playtip.setText("play");
         playbtn.setTooltip(playtip);
         playbtn.setGraphic(new ImageView(pause));
         playbtn.setGraphic(new ImageView(playimage));
         
         }
         else if(status == MediaPlayer.Status.PAUSED){
          player.play();
          player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
       
      double a = player.getCurrentTime().toMillis();
      int aa = (int)a;
       
       int milli=(int)aa%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)aa/1000;
          String ss=String.valueOf(second);
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
              
          milisec.setText(mm);
          sec.setText(ss);
          
           
   }
        });
                nfx.trans.play();
              //  transtab.play();
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
                playbtn.setGraphic(new ImageView(pause));
                player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                       System.out.println("M ON");
                       playtip.setText("play");
                       playbtn.setTooltip(playtip);
                       playbtn.setGraphic(new ImageView(playimage));
                       
                } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
                 }
           
     }
    
  @FXML
    void stopsound(ActionEvent event) throws MalformedURLException {
         
         
          String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\src\\"+"Icons\\";
                       // System.out.println("correct format"+cexedir);
    // String image1="file:\\"+cexedir+"record_1.png";
     String image2="file:\\"+cexedir+"play-arrow-(1).png";
       // Image record = new Image(image1,27,27,false,false);
        //  Image record = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\record_1.png",27,27,false,false);
       
         
          
       //  recordbtn1.setGraphic(new ImageView(record));
         count++;
         
         MediaPlayer.Status status = player.getStatus();
                 
         if (status == MediaPlayer.Status.PLAYING )
         { Image playimage = new Image(image2,15,17,false,false);
              player.stop();
              nfx.trans.stop();
//              transtab.stop();
              resetmedia();
              playtip.setText("play");
              playbtn.setTooltip(playtip);
              playbtn.setGraphic(new ImageView(playimage));
              
         }


    }
    
     @FXML
    void forwardseek(ActionEvent event) {
        
       player.seek(player.getCurrentTime().multiply(1.5));
       nfx.trans.playFrom(nfx.trans.getCurrentTime().multiply(1.5));
//        transtab.playFrom(trans.getCurrentTime().multiply(1.5));
        
    }
    
   
    @FXML
    void rewindseek(ActionEvent event) {
        player.seek(player.getCurrentTime().divide(1.5));
        nfx.trans.playFrom(nfx.trans.getCurrentTime().divide(1.5));
//         transtab.playFrom(trans.getCurrentTime().divide(1.5));
    }
    
    
       @FXML
    void rewindenter(MouseEvent event) {
        rewindtip.setText("rewind");
     rewindbtn.setTooltip(rewindtip);

    }
      @FXML
    void fwdenter(MouseEvent event) {
       forwardtip.setText("forward");
       fwdbtn.setTooltip(forwardtip);
    }
      @FXML
    void playenter(MouseEvent event) {
       playtip.setText("play");
      playbtn.setTooltip(playtip);
    }
      @FXML
    void stopenter(MouseEvent event) {
      stoptip.setText("stop");
       stopbtn.setTooltip(stoptip);
    }
     @FXML                                                          
    void recordenter(MouseEvent event) {
   //     recordtip.setText("record");
//        recordbtn.setTooltip(recordtip);
    }
   
    void resetmedia() throws MalformedURLException{
        
       //////need to change due to multi tab///// 
        
    //  String  filename = getfilename();
    //  File file = new File(filename);
         Media pick=nfx.getpick();
        //  pick = new Media(file.toURI().toURL().toExternalForm());
          player = new MediaPlayer(pick);
         // trans= new TranslateTransition();
          nfx.trans.stop();
     //     transtab.stop();           /////////////////comment this to solve the problem of play sound
      
           marker.setTranslateX(0);
         //  marker1.setTranslateX(0);  //////////////////comment this to solve the problem of play sound
         //  milisec.setText("000");
          // sec.setText("00");
         
         
         // System.out.println("The marker translate is :"+marker.getTranslateX());
          //System.out.println("The getX of the marker is :"+marker.getLayoutX());
    }
    
    
      @FXML
    void selectallfunction(ActionEvent event) 
    {
           //Graphics g;
        
       // this.paint(g);
       
        nfx.selectAll(numSamples, frames_per_pixel);
        
        
        
        
        
        

    }
    //@FXML
   

                        //End Selection
    @FXML
    void openwebpage(ActionEvent event) {
try {
    Desktop.getDesktop().browse(new URL("http://www.iitg.ac.in/shri/nS/").toURI());
} 
catch (Exception e)
{}
   
    }
   
    
    
    
    
    
    
    
        
        /////////////saveas function/////////
        
        @FXML
     void saveas(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, Exception
             
        { 
          Plotwave plot=new Plotwave();
            //System.out.println("sampling positions"+plot.getSamplingPositions());
    
          // byte[] current = streamBytes.getCurrent();
         //   System.out.println("current"+Arrays.toString(current));
//do        nfx.saveas(this.audioInputStream,frameSize, actual_frames_per_pixel);
        
        if(nfx.Is_record==1)
        {
            String filename = nfx.AfterRecordSave();
            Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("File is saved successfully!");

alert.showAndWait();
            
  /*         
            pick = new Media(new File(filename).toURI().toURL().toExternalForm());
            player = new MediaPlayer(pick);
            trans= new TranslateTransition();
        
             double[] samples = plot.readWaveData(filename);
             sendfilename(filename);
             this.audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
             AudioFormat audioFormat = audioInputStream.getFormat();
             float sampleRate = audioFormat.getSampleRate();
             System.out.println("The sample rate is : "+sampleRate);
             clearcheckmenuitem();
             tickmenuitem(sampleRate);
           
   //end of tickmenu function
        
        duration=plot.getduration();
        factor=plot.getsampfrq();
        frames_per_pixel=plot.getduration();
        //numSamples=plot.getnumsamples();
                   //  double[] fpoints;
                   // fpoints= rdpf.filter(samples);
                   //  int numsamples=fpoints.length;
                   int numsamples=samples.length;
        
        //double duration=numsamples/ factor;
        
        
  nfx.tempplot(classStage,filename,samples, numsamples, tabForPaste, TP, wavepane, factor, duration);
  
     */       
            
            
            
        
        }
        
        }
        
     
        @FXML
     void copy(ActionEvent event)
        {   
            Plotwave plot=new Plotwave();
          // byte[] current = streamBytes.getCurrent();
          //   System.out.println("current"+Arrays.toString(current));
          // nfx.saveas(this.audioInputStream,numSamples,frames_per_pixel);
///do  nfx.copy(this.audioInputStream, frameSize , actual_frames_per_pixel, frameRate);
          
            
            System.out.println("NoOfSampledCopied-->"+nfx.Get_No_Of_Samples_Copied() );
        }
        @FXML
        void paste(ActionEvent event) throws Exception
        {   int Samples_Copied = nfx.Get_No_Of_Samples_Copied();
           // Tab tab1 = new Tab();
           // tab1.setText("untitled ");
           //String filename = this.getfilename(); //fileName);
           // ScrollPane wavepane1=new ScrollPane();
           // tab1.setContent(wavepane1);
          //  TP.getTabs().add(tab1);
          //  TP.getSelectionModel().select(tab1);
           // TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            AudioInputStream inputStream = null;
           AudioInputStream shortenedStream = null;
    
      File file = new File(getfilename());
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
      AudioFormat format = fileFormat.getFormat();
      inputStream = AudioSystem.getAudioInputStream(file);
    //  int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
     // inputStream.skip(startSecond * bytesPerSecond);
      //long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
      shortenedStream = new AudioInputStream(inputStream, format, Samples_Copied);
      String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                        System.out.println("correct format"+cexedir);
                       
      String pasteFile=cexedir+"paste.wav";
      
      //String pasteFile ="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\paste.wav";//rightClick.saveLocation();                           later add record here
       File destinationFile = new File(pasteFile);
       AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
       Plotwave plot=new Plotwave();
      
       double[] samples = plot.readWaveData(new File(pasteFile));
       sendfilename(pasteFile);
     //RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.01);
     //duration=plot.getduration();
        factor=plot.getsampfrq();
        //numSamples=plot.getnumsamples();
                     double[] fpoints;
                    // fpoints= rdpf.filter(samples);
                     int numsamples=samples.length;
        double duration=numsamples/ factor;
        nfx.tempplot(classStage,pasteFile,samples, numsamples, tabForPaste, TP, wavepaneForPaste, factor, duration);
  
      
      
      
      
      
      
      
      
    //  nfx.tempplot(classStage, pasteFile, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration);
            
            
            
            
            
            
            
            
            
////do            nfx.paste(this.audioInputStream,tab1,TP,wavepane1);
            
        
        }
        
      private IntegerProperty timeSeconds = new SimpleIntegerProperty();
      private IntegerProperty timeSeconds1 = new SimpleIntegerProperty();
      private IntegerProperty timeSeconds2 = new SimpleIntegerProperty();      
      private Timeline timeline; 
      private Duration time = Duration.ZERO;
         
        
        
        
        
        
        
     
             @FXML
            void marker_play_during_record(){
                
            trans1.setDuration(Duration.millis(1000));
            trans1.setToX(1170);
            trans1.setNode(marker);
            trans1.play();
           
             }
           
           public Button returnbtn(){
               
               return recordbtn;             
          
           } 
    @FXML
    void recordAction(ActionEvent event)
    {
         Image pause = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\pausebtn.png",27,27,false,false);
         Image record = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\record_1.png",27,27,false,false);
         
         if(count%2==0){
         
         recordbtn1.setGraphic(new ImageView(pause));
         count++;
         
         }
         else
         {
             
         recordbtn1.setGraphic(new ImageView(record));
         count++;
         
         }
         
         
         
         
         

   } 
    ////////////////////////////Articulate Error/////////////////////
    ///////////////////////////Articulate_error///////////////
           
     ////////////////////////////Articulate Error/////////////////////
    ///////////////////////////Articulate_error///////////////
           
  
       @FXML
    void Articulate_D(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
        if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="D";
        String filename=nfx.getmultifilename();
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
         String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
           
             Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
    t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
    t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
     TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
         
       }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        } }

    @FXML
    void Articulate_T(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="T";
        String filename=nfx.getmultifilename();
         System.out.println("filename in articulate_error"+filename);
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
        String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
      //   Thread.sleep(4000);
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
           
             Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
    t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
    t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    // t2.setText("Severity Rating : 0.5423");
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
      TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
        }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }
    }

    @FXML
    void Articulate_g(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
 if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String  error="g";
         String filename=nfx.getmultifilename();
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
    String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
           
           Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
   t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
   t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
       TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
         
        }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }}

    @FXML
    void Articulate_d(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="d";
         String filename=nfx.getmultifilename();
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
    String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
            Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
  t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
  t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
     
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
         
        }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }}

    @FXML
    void Articulate_b(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="b";
        String filename=nfx.getmultifilename();
         String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
    String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
              Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
  t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
   t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
    
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
       
       }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        } }

    @FXML
    void Articulate_j(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="j";
       String filename=nfx.getmultifilename();
        String SampleRateConversion =SampleRateConversionForArti(new File(filename), (float) 16000.0);
   String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
           
             Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
    t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
    t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
        TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
      
         }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }}

    @FXML
    void Articulate_k(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="k";
       String filename=nfx.getmultifilename();
        System.out.println("filename in articulate_error"+filename);
         String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
  String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
        // Thread.sleep(4000);
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
           
              Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
    t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
    t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
   // t2.setText("Severity Rating : 0.484");
    
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    vb.getChildren().clear();
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
         
          }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");                                                                          

alert.showAndWait();
        }}

    @FXML
    void Articulate_p(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
        if(nfx.Get_No_Of_Samples_Copied()>1){
        String error="p";
        String filename=nfx.getmultifilename();
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
//        System.out.println("mouse position in arti\t"+plot.getMouseStartpos());
        String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
       //  Thread.sleep(4000);
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
           
            Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
    t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
   t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
   t2.setText(nfx.geterror().trim());
   //  t2.setText("Severity Rating : 0.870");
   
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
       TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
       
        }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }
    }

    @FXML
    void Articulate_J(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="J";
        String filename=nfx.getmultifilename();
       
         String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
   String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
           
            Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
    t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
    t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
        TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
      
          }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }}

    @FXML
    void Articulate_h(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="h";
       String filename=nfx.getmultifilename();
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
  String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
            Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
  t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
    t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
        
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
      
         }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }}

    
    @FXML
    void Articulate_s(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="s";
      String filename=nfx.getmultifilename();
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
  String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
         
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
            Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
  t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
    t.setFont(Font.font ("Verdana", 20));
   t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
        
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
      
         }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }}

    
    
    
    
    @FXML
    void Articulate_t(ActionEvent event) throws IOException, InterruptedException, UnsupportedAudioFileException {
  if(nfx.Get_No_Of_Samples_Copied()>1){
       
        String error="t";
      String filename=nfx.getmultifilename();
        String SampleRateConversion = SampleRateConversionForArti(new File(filename), (float) 16000.0);
   String Articulate_error = nfx.Articulate_error(SampleRateConversion,error);
        // Thread.sleep(4000);
          Tab tab = new Tab();
           tab.setText("Articulation_error Result ");
         //  String filename = this.getfilename(); //fileName);
            Text t = new Text();
           Text t1 = new Text();
            Text t2 = new Text();
   // String value = Double.toString(probability);
    VBox vb=new VBox();
    t.setText("The Mahalobin's distance is :"+nfx.getmahadistance() );
   t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.GREEN);
    
    t2.setText(nfx.geterror().trim());
  //  t2.setText("Severity Rating: 0.4616");
  
    t2.setFont(Font.font ("Verdana", 20));
    t2.setFill(Color.GREEN);
    
    t1.setText("Articulation error present");
    t1.setFont(Font.font ("Verdana", 20));
    t1.setFill(Color.GREEN);
    
    vb.getChildren().addAll(t,t2,t1);
    tab.setContent(vb);
       
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            
          
            
            
            
            
            
       
         }else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse Articulation Error");

alert.showAndWait();
        }} 
     
    
    
    
    
}
 

 
 
 

 
     
    
    
    
    


