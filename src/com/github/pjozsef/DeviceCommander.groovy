package com.github.pjozsef

class DeviceCommander {
    def androidHome
    def devices

    DeviceCommander(androidHome, devices) {
        this.androidHome = androidHome
        this.devices = devices
    }

    def execute(command){
        for(device in devices){
            "echo Executing $command on $device".execute()
            "${androidHome}platform-tools/adb -s $device $command".execute()
        }
    }
}