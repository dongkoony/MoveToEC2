package com.mte.dto;

public class ColletorTargetInfoDto {
    private String ipAddress;
    private String hostName;
    private String osName;
    private String osVersion;
    private String memorySize;
    private String volumeSize;
    private String cpuVendor;
    private String cpuModel;
    private String cpuMhz;
    private String cpuCore;
    private String cpuSocket;
    private String cpuVirtualCore;
    private String hyperThreadingYn;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getVolumeSize() {
        return volumeSize;
    }

    public void setVolumeSize(String volumeSize) {
        this.volumeSize = volumeSize;
    }

    public String getCpuVendor() {
        return cpuVendor;
    }

    public void setCpuVendor(String cpuVendor) {
        this.cpuVendor = cpuVendor;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public String getCpuMhz() {
        return cpuMhz;
    }

    public void setCpuMhz(String cpuMhz) {
        this.cpuMhz = cpuMhz;
    }

    public String getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(String cpuCore) {
        this.cpuCore = cpuCore;
    }

    public String getCpuSocket() {
        return cpuSocket;
    }

    public void setCpuSocket(String cpuSocket) {
        this.cpuSocket = cpuSocket;
    }

    public String getCpuVirtualCore() {
        return cpuVirtualCore;
    }

    public void setCpuVirtualCore(String cpuVirtualCore) {
        this.cpuVirtualCore = cpuVirtualCore;
    }

    public String getHyperThreadingYn() {
        return hyperThreadingYn;
    }

    public void setHyperThreadingYn(String hyperThreadingYn) {
        this.hyperThreadingYn = hyperThreadingYn;
    }
}
