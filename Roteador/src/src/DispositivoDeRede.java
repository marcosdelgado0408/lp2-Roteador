package src;

public abstract class DispositivoDeRede {
    protected String Ip;
    protected String Mac;

    public abstract void setIP(String Ip);
    public abstract String getIp();

    public abstract void setMac(String Mac);
    public abstract String getMac();

}
