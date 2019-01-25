/*
 * SIVOTEK SOLUTIONS LTD COURSE GRADING SYSTEM. 
 * DEVELOPED BY SIVOTEK SOLUTION LTD.
 * ALL RIGHTS RESERVED 2017.
 */
package com.sivotek.school_management_system.app.crud;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author Administrator
 */
public class ClientDetails extends HttpServlet
  {
  private HttpServletRequest requests;
  //protected HttpSession session;

  private String userAgent;
  private String deviceName;       // Device Full Name
  private String company;          // Trade Name of manufacturer
  private String name;             // Identification of the browser
  private String version;          // Version
  private String mainVersion;      // main version
  private String minorVersion;     // minor version
  private String os;               // operating system
  private String language = "en";  // language code
  private Locale locale;           // Local object with the current
                                     // Language settings
  private String sessionId;
  private String remoteAddress;

  private Hashtable supportedLanguages; // Supported languages

  public ClientDetails(HttpServletRequest request)
    {
    this.initialize();
    this.requests = request;
    String agent = this.requests.getHeader("User-Agent").toString();
    //
    this.setUserAgent(agent);
    
    this.setCompany();
    this.setName();
    this.setVersion();
    this.setMainVersion();
    this.setMinorVersion();
    this.setOs();
    this.setLanguage();
    this.setLocale();
    }

   private void initialize()
    {
      this.supportedLanguages = new Hashtable(2);
      this.supportedLanguages.put("en", "");
      
    }

   public void setUserAgent(String httpUserAgent)
    {
     this.userAgent = httpUserAgent.toLowerCase();
     //System.out.println("User Agent :" + this.requests.getHeader("User-Agent").toLowerCase());
     
    }

  private void setCompany()
    {
    if (this.userAgent.indexOf("msie") > -1)
      {
      this.company = "Microsoft";
      System.out.println("Company :" + this.company);
     
      }
     else if (this.userAgent.indexOf("opera") > -1)
      {
       this.company = "Opera Software";
       System.out.println("Company :" + this.company);
      }
     else if (this.userAgent.indexOf("mozilla") > -1)
      {
       this.company = "Netscape Communications";
       System.out.println("Company :" + this.company);
      }
     
    else
      {
      this.company = "unknown";
      }
    }

  /**
   * Returns the company name of the manufacturer of the browser.
   */
  public String getCompany()
    {
    return this.company;
    }

  private void setName()
    {
    if (this.company == "Microsoft")
      {
       this.name = "Microsoft Internet Explorer";
       System.out.println("name :" + this.name);
      }
     else if (this.company == "Netscape Communications")
      {
       this.name = "Netscape Navigator";
       System.out.println("name :" + this.name);
      }
     else if (this.company == "Operasoftware")
      {
       this.name = "Operasoftware Opera";
       System.out.println("name :" + this.name);
      }
    else
      {
       this.name = "unknown";
      }
    }

  /**
   * Get the name of the browser.
   */
  public String getName()
  {
    return this.name;
  }

  private void setVersion()
  {
    int tmpPos;
    String tmpString;

    if (this.company == "Microsoft")
    {
      String str = this.userAgent.substring(this.userAgent.indexOf("msie") + 5);
      this.version = str.substring(0, str.indexOf(";"));
      System.out.println("version :" + this.version);
    }
    else
    {
     tmpString = (this.userAgent.substring(tmpPos = (this.userAgent.indexOf("/")) + 1,tmpPos + this.userAgent.indexOf(" "))).trim();
     this.version = tmpString.substring(0, tmpString.indexOf(" "));
     System.out.println("version :" + this.version);
    }
  }

  /**
   * Returns the version number of the browser.
   */
  public String getVersion()
  {
    return this.version;
  }

  private void setMainVersion()
  {
     this.mainVersion = this.version.substring(0, this.version.indexOf("."));
     System.out.println("mainVersion :" + this.mainVersion);
  }

  /**
   * Returns the major version number of the browser.
   */
  public String getMainVersion()
  {
    return this.mainVersion;
  }

  private void setMinorVersion()
  {
     this.minorVersion = this.version.substring(this.version.indexOf(".") + 1).trim();
  }

  /**
   * Returns the minor version number of the browser.
   */
  public String getMinorVersion()
    {
     return this.minorVersion;
    }

  private void  setOs()
    {
    if (this.userAgent.indexOf("win") > -1)
      {
      if (this.userAgent.indexOf("windows nt 6.1") > -1 || this.userAgent.indexOf("win7") > -1)
        {
        this.os = "Windows 7";
        }
      if (this.userAgent.indexOf("windows 95") > -1 || this.userAgent.indexOf("win95") > -1)
        {
        this.os = "Windows 95";
        }
      if (this.userAgent.indexOf("windows 98") > -1 || this.userAgent.indexOf("win98") > -1)
        {
        this.os = "Windows 98";
        }
      if (this.userAgent.indexOf("windows nt") > -1 || this.userAgent.indexOf("winnt") > -1)
        {
        this.os = "Windows NT";
        }
      if (this.userAgent.indexOf("win16") > -1 || this.userAgent.indexOf("windows 3.") > -1)
        {
        this.os = "Windows 3.x";
        }
      }
    
    else if(this.userAgent.indexOf("symbian") > -1 )
    {
        if(this.userAgent.indexOf("symbian/1") > -1 || this.userAgent.indexOf("symbian 1") > -1){
          this.os = "Symbian 1";
        }
        if(this.userAgent.indexOf("symbian/2") > -1 || this.userAgent.indexOf("symbian 2") > -1){
          this.os = "Symbian 2";
        }
        if(this.userAgent.indexOf("symbian/3") > -1 || this.userAgent.indexOf("symbian 3") > -1){
          this.os = "Symbian Belle";
        }
    }
    
     else if (this.userAgent.indexOf("Mac") > -1 ) {
            if (this.userAgent.indexOf("Mac_PowerPC") > -1 || this.userAgent.indexOf("Mac_PPC") > -1) {
                this.os = "Macintosh Power PC";
            } else
                if (this.userAgent.indexOf("Macintosh") > -1) {
                    this.os = "Macintosh";
                }
      else {
                      this.os = "Unknown Mac";
                }
        }
        
    }
  /**
   * Get the name of the operating system.
   */
  public String getOs()
    {
     return this.os;
    }

  private void setLanguage()
    {
    String prefLanguage = this.requests.getHeader("Accept-Language");

    if (prefLanguage != null)
      {
       String language = null;
       StringTokenizer st = new StringTokenizer(prefLanguage, ",");
       int elements = st.countTokens();
       for(int idx = 0; idx != elements; idx++)
        {
         if (this.supportedLanguages.containsKey((language = st.nextToken())))
            {
             this.language = this.parseLocale(language);
            }
        }
      }
    }

  /*
   * Auxiliary function for setLanguage().
   */
  private String parseLocale(String language)
   {
    StringTokenizer st = new StringTokenizer(language, "-");

    if (st.countTokens() == 2)
      {
      return st.nextToken();
  }
  else
   {
    return language;
   }
  }

  /**
   * Returns the country code of the user
   * preferred language.
   */
  public String getLanguage()
    {
    return this.language;
    }

  private void setLocale()
    {
    this.locale = new Locale(this.language, "");
    }

  /**
   * Returns an object with the locale of the browser language preference
   */
  public Locale getLocale()
    {
    return this.locale;
    }
  
   public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = this.requests.getRequestedSessionId();
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = this.requests.getRemoteAddr();
    }
  }