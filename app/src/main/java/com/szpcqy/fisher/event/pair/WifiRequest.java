package com.szpcqy.fisher.event.pair;

public class WifiRequest {

        private String ssid;
        private String sspw;

        public WifiRequest(String ssid, String sspw){
                this.ssid = ssid;
                this.sspw = sspw;
        }


        public String getSsid() {
                return ssid;
        }

        public String getSspw() {
                return sspw;
        }
}
