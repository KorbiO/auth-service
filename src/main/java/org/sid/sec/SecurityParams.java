package org.sid.sec;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="omar@korbi.net";
    public static final long EXPIRATION=100*24*3600;
    public static final String HEADER_PREFIX="Bearer ";
}
