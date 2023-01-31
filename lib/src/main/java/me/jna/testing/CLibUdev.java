package me.jna.testing;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * The {@code CLibUdev} interface defines methods for interacting with the udev
 * library in Linux. It provides methods for creating a new udev library
 * context, freeing resources associated with a udev library context, creating a
 * new udev monitor, enabling receiving of udev events, receiving data from the
 * udev monitor socket, getting the devpath and properties of a udev device, and
 * freeing resources associated with a udev device.
 * <p>
 * The implementation of the interface uses JNA to interact with the C library.
 * To use the interface, call the {@code INSTANCE} field, which loads the
 * library and returns an instance of the interface.
 * <p>
 * Example:
 *
 * <pre>
 * CLibUdev udev = CLibUdev.INSTANCE;
 * Pointer udevContext = udev.udev_new();
 * Pointer udevMonitor = udev.udev_monitor_new_from_netlink(udevContext, "udev");
 * udev.udev_monitor_enable_receiving(udevMonitor);
 * Pointer udevDevice = udev.udev_monitor_receive_device(udevMonitor);
 * String devPath = udev.udev_device_get_devpath(udevDevice);
 * String propertyValue = udev.udev_device_get_property_value(udevDevice, "PROPERTY_KEY");
 * udev.udev_device_unref(udevDevice);
 * udev.udev_unref(udevContext);
 * </pre>
 */
public interface CLibUdev extends Library {
   /**
    * Singleton instance of the CLibUdev.
    */
   CLibUdev INSTANCE = Native.load("udev", CLibUdev.class);

   /**
    * Get the devpath of a udev device.
    *
    * @param udev_device Pointer to the udev device.
    * @return The devpath of the udev device.
    */
   String udev_device_get_devpath(Pointer udev_device);

   /**
    * Retrieve the list of properties of the device.
    *
    * @param udev_device udev device object
    * @return a pointer to the first entry of the list of properties, or `null` if
    *         the device has no properties
    */
   Pointer udev_device_get_properties_list_entry(Pointer udev_device);

   /**
    * Get the property value of a udev device.
    *
    * @param udev_device Pointer to the udev device.
    * @param key         Key of the property to get the value for.
    * @return The value of the property for the given key.
    */
   String udev_device_get_property_value(Pointer udev_device, String key);

   /**
    * Create a new udev device object and fill in information from the sys device.
    *
    * @param udev    udev library context
    * @param syspath sys device path of the device to create
    * @return a pointer to the newly created udev device object, or `null` if an
    *         error occurs
    */
   Pointer udev_device_new_from_syspath(Pointer udev, String syspath);

   /**
    * Free resources associated with a udev device.
    *
    * @param udev_device Pointer to the udev device to be freed.
    */
   void udev_device_unref(Pointer udev_device);

   /**
    * Adds a match to filter for a specific subsystem.
    *
    * @param udev_enumerate - The udev enumerate context.
    * @param subsystem      - The subsystem to match against.
    * @return 0 on success, otherwise a negative error code.
    */
   int udev_enumerate_add_match_subsystem(Pointer udev_enumerate, String subsystem);

   /**
    * Gets the list of the enumerated devices.
    *
    * @param udev_enumerate - The udev enumerate context.
    * @return A pointer to the list of enumerated devices.
    */
   Pointer udev_enumerate_get_list_entry(Pointer udev_enumerate);

   /**
    * Creates a new udev enumerate context.
    *
    * @param udev - The udev library context.
    * @return A pointer to the newly created udev enumerate context.
    */
   Pointer udev_enumerate_new(Pointer udev);

   /**
    * Scans sysfs for devices and adds the ones that match the filter to the list
    * of enumerated devices.
    *
    * @param udev_enumerate - The udev enumerate context.
    * @return 0 on success, otherwise a negative error code.
    */
   int udev_enumerate_scan_devices(Pointer udev_enumerate);

   /**
    * Frees resources associated with a udev enumerate context.
    *
    * @param udev_enumerate - The udev enumerate context.
    */
   void udev_enumerate_unref(Pointer udev_enumerate);

   /**
    * Retrieve the name of the property of the given entry.
    *
    * @param entry a property entry
    * @return the name of the property, or `null` if the entry is invalid
    */
   String udev_list_entry_get_name(Pointer entry);

   /**
    * Retrieve the next entry in the list of properties.
    *
    * @param entry a property entry
    * @return a pointer to the next entry in the list of properties, or `null` if
    *         the entry is the last one in the list
    */
   Pointer udev_list_entry_get_next(Pointer entry);

   /**
    * Retrieve the value of the property of the given entry.
    *
    * @param entry a property entry
    * @return the value of the property, or `null` if the entry is invalid
    */
   String udev_list_entry_get_value(Pointer entry);

   /**
    * Enable receiving of udev events for a udev monitor.
    *
    * @param udev_monitor Pointer to the udev monitor.
    * @return 0 on success, a negative value on failure.
    */
   int udev_monitor_enable_receiving(Pointer udev_monitor);

   /**
    * Create a new udev monitor for the given name.
    *
    * @param udev Pointer to the udev context.
    * @param name Name of the udev monitor.
    * @return Pointer to the newly created udev monitor.
    */
   Pointer udev_monitor_new_from_netlink(Pointer udev, String name);

   /**
    * Receive a udev device from the udev monitor socket.
    *
    * @param udev_monitor Pointer to the udev monitor.
    * @return Pointer to the received udev device.
    */
   Pointer udev_monitor_receive_device(Pointer udev_monitor);

   /**
    * Create a new udev library context.
    *
    * @return Pointer to the newly created udev context.
    */
   Pointer udev_new();

   /**
    * Free resources associated with a udev library context.
    *
    * @param udev Pointer to the udev context to be freed.
    */
   void udev_unref(Pointer udev);

}

class JNAUdevExample {
   public static void main(String[] args) {
      CLibUdev libudev = CLibUdev.INSTANCE;
      Pointer udev = libudev.udev_new();
      Pointer dev = libudev.udev_device_new_from_syspath(udev, "/sys/class/net/eth0");
      Pointer entry = libudev.udev_device_get_properties_list_entry(dev);

      while (entry != null) {
         String name = libudev.udev_list_entry_get_name(entry);
         String value = libudev.udev_list_entry_get_value(entry);
         System.out.println(name + "=" + value);
         entry = libudev.udev_list_entry_get_next(entry);
      }

      libudev.udev_device_unref(dev);
      libudev.udev_unref(udev);
   }
}

class Main {
   public static void main(String[] args) {
      CLibUdev udev = CLibUdev.INSTANCE;
      Pointer udevHandle = udev.udev_new();

      Pointer udevMonitor = udev.udev_monitor_new_from_netlink(udevHandle, "udev");
      udev.udev_monitor_enable_receiving(udevMonitor);

      while (true) {
         Pointer udevDevice = udev.udev_monitor_receive_device(udevMonitor);
         String devPath = udev.udev_device_get_devpath(udevDevice);
         String vendor = udev.udev_device_get_property_value(udevDevice, "ID_VENDOR");
         String product = udev.udev_device_get_property_value(udevDevice, "ID_MODEL");
         System.out.println(
               "Device Connected: " + devPath + " Vendor: " + vendor + " Product: " + product);
         udev.udev_device_unref(udevDevice);
      }
   }
}
