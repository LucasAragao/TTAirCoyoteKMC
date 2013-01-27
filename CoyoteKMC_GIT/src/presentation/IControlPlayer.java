package presentation;

import javax.swing.JComponent;
import presentation.impl.KinectMotionCapture.KinectControl.KinectAccess;
import presentation.impl.KinectMotionCapture.KinectControl.KinectManager;

public interface IControlPlayer {
   public void start()throws Exception;
   public void stop()throws Exception;
   public void pause()throws Exception;
   public void back()throws Exception;
   public void next()throws Exception;
   public void fastForward()throws Exception;
   public void fastRewind()throws Exception;
   public void playWithRate(float rate)throws Exception;
   public int getFullTime()throws Exception;
   public int getTimeBegin()throws Exception;
   public int getTimeEnd()throws Exception;
   public int getCurrentTime()throws Exception;
   public void go2Time(int time)throws Exception;
   public void setTimeStampBegin(int timeStamp) throws Exception;
   public void setTimeStampEnd(int timeStamp) throws Exception;
   public void setPlayOnlyInTimestamp(boolean b)throws Exception;
   public boolean isToPlayOnlyInTimestamp();
   public void verifyIfIsToPlayInterval(int currentTime) throws Exception;
   public void open(String uri)throws Exception;
   public void close()throws Exception;
   public JComponent getVisualDisplay()throws Exception;
   public JComponent getVisualControl()throws Exception;
   
}
