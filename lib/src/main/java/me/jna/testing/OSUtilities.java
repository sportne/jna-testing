package me.jna.testing;

public class OSUtilities {

   /**
    * Enumeration of possible operating systems.
    */
   public enum OS {
      WINDOWS, MAC, LINUX, UNIX, UNKNOWN
   }

   /**
    * Determines what the current operating system is.
    * 
    * @return the current operating system.
    */
   public static OS determineOS() {
      String os = System.getProperty("os.name").toLowerCase();
      if (os.contains("windows")) {
         return OS.WINDOWS;
      } else if (os.contains("mac")) {
         return OS.MAC;
      } else if (os.contains("linux")) {
         return OS.LINUX;
      } else if (os.contains("unix")) {
         return OS.UNIX;
      }
      return OS.UNKNOWN;
   }

}
