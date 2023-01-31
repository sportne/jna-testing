package me.jna.testing;

import com.sun.jna.Pointer;

public class CLibUdevWrapper implements AutoCloseable {

   private CLibUdev udev;
   private Pointer udevPointer;
   
   CLibUdevWrapper(CLibUdev udev)
   {
      this.udev = udev;
      udevPointer = udev.udev_new();
   }

   @Override
   public void close() throws Exception {
      udev.udev_unref(udevPointer); 
   }
   
}
