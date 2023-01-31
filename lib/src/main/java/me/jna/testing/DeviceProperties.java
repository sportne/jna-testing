package me.jna.testing;

import com.sun.jna.Pointer;

public class DeviceProperties {

   public static DeviceProperties createDeviceProperties(CLibUdev udev, Pointer udevDevice) {
      String devPath = udev.udev_device_get_devpath(udevDevice);
      String action = udev.udev_device_get_property_value(udevDevice, "ACTION");
      String vendor = udev.udev_device_get_property_value(udevDevice, "ID_VENDOR");
      String product = udev.udev_device_get_property_value(udevDevice, "ID_MODEL");
      String subsystem = udev.udev_device_get_property_value(udevDevice, "SUBSYSTEM");
      String devType = udev.udev_device_get_property_value(udevDevice, "DEVTYPE");
      String driver = udev.udev_device_get_property_value(udevDevice, "DRIVER");
      String idBus = udev.udev_device_get_property_value(udevDevice, "ID_BUS");
      String idModelId = udev.udev_device_get_property_value(udevDevice, "ID_MODEL_ID");
      String idRevision = udev.udev_device_get_property_value(udevDevice, "ID_REVISION");
      String idSerial = udev.udev_device_get_property_value(udevDevice, "ID_SERIAL");
      String idSerialShort = udev.udev_device_get_property_value(udevDevice, "ID_SERIAL_SHORT");
      String idType = udev.udev_device_get_property_value(udevDevice, "ID_TYPE");
      String idVendorId = udev.udev_device_get_property_value(udevDevice, "ID_VENDOR_ID");

      return new DeviceProperties(action, devPath, subsystem, devType, driver, idBus, product,
            idModelId, idRevision, idSerial, idSerialShort, idType, vendor, idVendorId);
   }

   private final String action;
   private final String devPath;
   private final String subsystem;
   private final String devType;
   private final String driver;
   private final String idBus;
   private final String idModel;
   private final String idModelId;
   private final String idRevision;
   private final String idSerial;
   private final String idSerialShort;
   private final String idType;
   private final String idVendor;
   private final String idVendorId;

   DeviceProperties(String action, String devPath, String subsystem, String devType, String driver,
         String idBus, String idModel, String idModelId, String idRevision, String idSerial,
         String idSerialShort, String idType, String idVendor, String idVendorId) {
      this.action = action;
      this.devPath = devPath;
      this.subsystem = subsystem;
      this.devType = devType;
      this.driver = driver;
      this.idBus = idBus;
      this.idModel = idModel;
      this.idModelId = idModelId;
      this.idRevision = idRevision;
      this.idSerial = idSerial;
      this.idSerialShort = idSerialShort;
      this.idType = idType;
      this.idVendor = idVendor;
      this.idVendorId = idVendorId;
   }

   public String getAction() {
      return action;
   }

   public String getDevPath() {
      return devPath;
   }

   public String getDevType() {
      return devType;
   }

   public String getDriver() {
      return driver;
   }

   public String getIdBus() {
      return idBus;
   }

   public String getIdModel() {
      return idModel;
   }

   public String getIdModelId() {
      return idModelId;
   }

   public String getIdRevision() {
      return idRevision;
   }

   public String getIdSerial() {
      return idSerial;
   }

   public String getIdSerialShort() {
      return idSerialShort;
   }

   public String getIdType() {
      return idType;
   }

   public String getIdVendor() {
      return idVendor;
   }

   public String getIdVendorId() {
      return idVendorId;
   }

   public String getSubsystem() {
      return subsystem;
   }

}
