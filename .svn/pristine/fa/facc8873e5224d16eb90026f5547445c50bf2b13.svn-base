package com.zllh.utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;

import cfca.sm2rsa.common.CBCParam;
import cfca.sm2rsa.common.Mechanism;
import cfca.sm2rsa.common.PKIException;
import cfca.util.CertUtil;
import cfca.util.EncryptUtil;
import cfca.util.EnvelopeUtil;
import cfca.util.KeyUtil;
import cfca.util.SignatureUtil2;
import cfca.util.cipher.lib.JCrypto;
import cfca.util.cipher.lib.Session;
import cfca.x509.certificate.X509Cert;
import cfca.x509.certificate.X509CertHelper;
import cfca.x509.certificate.X509CertValidator;


/**
 * @ClassName CfcUtil
 * @Description CFC工具类(此类中带sys.out的方法都为例子用到时可改动)
 * @author JW
 * @Date 2016年8月23日 上午10:48:14
 * @version 1.0.0
 */
public class CfcUtil {
    
    /** PFX测试文件(正式发布可以换成正式的文件)在项目中的路径/cfcData/test.jks */
    public static final String PFXPATH = File.separatorChar + "cfcData" + File.separatorChar + "test.pfx";
    
    /** JKS测试文件(正式发布可以换成正式的文件)在项目中的路径/cfcData/test.pfx */
    public static final String JKSPATH = File.separatorChar + "cfcData" + File.separatorChar + "test.jks";
    
    /** SM2测试文件(正式发布可以换成正式的文件)在项目中的路径/cfcData/sm2pfx.SM2 */
    public static final String SM2PATH = File.separatorChar + "cfcData" + File.separatorChar + "sm2pfx.SM2";
    
    /** P7B测试文件(正式发布可以换成正式的文件)在项目中的路径/cfcData/test.p7b */
    public static final String P7BPATH = File.separatorChar + "cfcData" + File.separatorChar + "test.p7b";

    //...TODO  所有加密正式测试文件都在cfcData目录下用到自己加
    
    /** 秘钥长度 */
    public static final Integer LEN_1024 = 1024;
    public static final Integer LEN_2048 = 2048;
    public static final Integer LEN_4096 = 4096;
    
    /** 签名算法 */
    public static final String ALG_SHA1_RSA   = Mechanism.SHA1_RSA;
    public static final String ALG_SHA256_RSA = Mechanism.SHA256_RSA;
    public static final String ALG_SHA512_RSA = Mechanism.SHA512_RSA;
    public static final String ALG_MD5_RSA    = Mechanism.MD5_RSA;
    
    /** 加密/解密 算法 */
    public static final String SYMMETRICALGORITHM_RC4 = Mechanism.RC4;
    /** DESede/CBC/PKCS7Padding */
    public static final String SYMMETRICALGORITHM_DES3_CBC = Mechanism.DES3_CBC;
    /** SM4/CBC/PKCS7Padding */
    public static final String SYMMETRICALGORITHM_SM4_CBC = Mechanism.SM4_CBC;;
    
    /** 对称密钥算法 */
    public static final String KEYTYPE_DES3_KEY = Mechanism.DES3_KEY;
    public static final String KEYTYPE_SM4_KEY = Mechanism.SM4_KEY;
    public static final String KEYTYPE_RC4_KEY = Mechanism.RC4_KEY;
    
    public static Session session = null;
    static{
        try {
            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
        } catch (PKIException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @Title: getPath 
     * @Description: 获取各类加密方式的证书文件目录
     * @param @param request     HttpServletRequest
     * @param @param filePath    证书文件在项目中的具体位置
     * @param @return
     * @return String
     */
    public String getPath(HttpServletRequest request, String filePath){
        return request.getSession().getServletContext().getRealPath(filePath);
    }
    
    /**
     * @Title: getCertFromPfx 
     * @Description: 获得PFX中的证书
     * @param pfxPath    PFX证书路径
     * @param pwd        PFX证书密码
     * @throws Exception
     * @return void
     */
    public void getCertFromPfx(String pfxPath, String pwd) throws Exception {
        X509Cert x509Cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        System.out.println("证书主题为:" + x509Cert.getSubject());
        System.out.println("获得证书序列号:" + x509Cert.getStringSerialNumber());
    }    
    
    /**
     * @Title: getCertFromJks 
     * @Description: 获得JKS中的证书
     * @param jksPath    JKS证书路径
     * @param pwd        JKS证书密码
     * @throws Exception JKS证书别名
     * @return void
     */
    public void getCertFromJks(String jksPath, String pwd, String alias) throws Exception {
        X509Cert x509Cert = CertUtil.getCertFromJks(jksPath, pwd, alias);
        System.out.println("证书主题为:" + x509Cert.getSubject());
        System.out.println("获得证书序列号:" + x509Cert.getStringSerialNumber());
    }
    
    /**
     * @Title: getCertFromSM2 
     * @Description: 获得SM2中的证书
     * @param sm2pfxPath   SM2证书路径
     * @throws Exception
     * @return void
     */
    public void getCertFromSM2(String sm2pfxPath) throws Exception {
        X509Cert sm2Cert = CertUtil.getCertFromSM2(sm2pfxPath);
        System.out.println("证书主题为:" + sm2Cert.getSubject());
        System.out.println("获得证书序列号:" + sm2Cert.getStringSerialNumber());
    }
    
    /**
     * @Title: getPrivateKeyFromPfx 
     * @Description: 获得PFX中的私钥
     * @param pfxPath    PFX证书路径
     * @param pwd        PFX证书密码
     * @throws Exception
     * @return void
     */
    public String getPrivateKeyFromPfx(String pfxPath, String pwd) throws Exception {
        return KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd).toString();
    }
    
    /**
     * @Title: getPrivateKeyFromJks 
     * @Description: 获得JKS中的私钥
     * @param jksPath     JKS证书路径
     * @param pwd         JKS证书密码
     * @param alias       JKS证书别名
     * @throws Exception
     * @return void
     */
    public String getPrivateKeyFromJks(String jksPath, String pwd, String alias) throws Exception {
        return KeyUtil.getPrivateKeyFromJKS(jksPath, pwd, alias).toString();
    }
    
    /**
     * @Title: getPrivateKeyFromSM2 
     * @Description: 获得SM2中的私钥
     * @param sm2pfxPath   SM2证书路径
     * @param pwd          SM2证书密码
     * @throws Exception
     * @return void
     */
    public String getPrivateKeyFromSM2(String sm2pfxPath, String pwd) throws Exception {
        return KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd).toString();
    }
    
    /**
     * @Title: getP7bChainCerts 
     * @Description: 获得p7b证书链
     * @param p7bFilePath   p7b文件路径
     * @throws Exception
     * @return void
     */
    public void getP7bChainCerts(String p7bFilePath) throws Exception {
        X509Cert[] certs = CertUtil.parseP7b(p7bFilePath);
        System.out.println("证书张数：" + certs.length);
        for (int i = 0; i < certs.length; i++) {
            System.out.println("证书" + i + " serial number：" + certs[i].getStringSerialNumber());
        }
    }
    
    /**
     * @Title: generateX509Cert 
     * @Description: 产生X509Cert对象
     * @param cerPath        CER文件路径
     * @throws Exception
     * @return void
     */
    public String generateX509Cert(String cerPath) throws Exception {
        X509Cert cert = X509CertHelper.parse(cerPath);
        //证书 serial number
        return cert.getStringSerialNumber();
    }
    
    /**
     * @Title: getCertExtensionData 
     * @Description: 获取拓展域对象
     * @param cerPath     CER文件路径,例如./TestData/extension.cer
     * @param oidStr      OID,例如:1.2.86.1
     * @throws Exception
     * @return String
     */
    public String getCertExtensionData(String cerPath, String oidStr) throws Exception {
        X509Cert cert = X509CertHelper.parse(cerPath);
        byte[] extension = CertUtil.getCertExtensionData(cert, oidStr);
        //拓展数据
        return new String(extension, "GBK");
    }
    
    /**
     * @Title: verifyRSACertAll 
     * @Description: 验证证书签名 有效期 CRL
     * @param cerPath        CER文件路径
     * @param cerstPaths[]   受信任证书的路径
     * @param clrPath        CRL文件路径
     * @throws Exception
     * @return void
     */
    public void verifyRSACertAll(String cerPath, String[] cerstPaths, String clrPath) throws Exception {
        FileInputStream bais = new FileInputStream(cerPath);
        byte[] derData = new byte[bais.available()];
        bais.read(derData);
        bais.close();
        X509Cert userCert = new X509Cert(derData);

        if (X509CertValidator.verifyCertDate(userCert)) {
            System.out.println("date is valid:" + userCert.getNotBefore() + "---" + userCert.getNotAfter());
        } else {
            System.out.println("out of date:" + userCert.getNotBefore() + "---" + userCert.getNotAfter());
        }

        System.out.println("即将验证证书的签名，请配置相应的受信任的证书路径!!!");
        for(String path : cerstPaths){
            X509CertValidator.updateTrustCertsMap(path);
        }
        if (X509CertValidator.validateCertSign(userCert)) {
            System.out.println("signature is right!");
        } else {
            System.out.println("signature is wrong!");
        }

        if (X509CertValidator.verifyCertByCRLOutLine(userCert, clrPath)) {
            System.out.println("is valid in crl");
        } else {
            System.out.println("is revoked in crl");
        }
    }
    
    /**
     * @Title: generateRSAKeyPair 
     * @Description: 产生RSA密钥对
     * @param len  密钥长度:1.1024  2.2048  3.4096      
     * @throws Exception
     * @return void
     */
    public void generateRSAKeyPair(Integer len) throws Exception {
        KeyPair pair = KeyUtil.generateRSAKeyPair(new Mechanism(Mechanism.RSA), len, session);
        System.out.println("RSA私钥：" + pair.getPrivate().toString());
        System.out.println("RSA公钥：" + pair.getPublic().toString());
    }
    
    /**
     * @Title: generateSM2KeyPair 
     * @Description: 产生SM2密钥对
     * @return void
     */
    public void generateSM2KeyPair() {
        KeyPair pair = KeyUtil.generateSM2KeyPair(new Mechanism(Mechanism.SM2), session);
        System.out.println("SM2私钥：" + pair.getPrivate().toString());
        System.out.println("SM2公钥：" + pair.getPublic().toString());
    }
    
    /**
     * @Title: rsaP1Sign 
     * @Description: RSA消息签名(PKCS#1)
     * @param srcData     消息原文
     * @param pfxPath     PFX证书路径
     * @param pwd         PFX证书密码
     * @param alg         签名算法
     * @throws Exception
     * @return String
     */
    public String rsaP1Sign(String srcData, String pfxPath, String pwd, String alg) throws Exception {
        PrivateKey priKey = (PrivateKey) getRSAPriKey(pfxPath, pwd);
        byte[] signature = new SignatureUtil2().p1SignMessage(alg, srcData.getBytes("UTF8"), priKey, session);
        return new String(signature);

    }
    
    /**
     * @Title: getRSAPriKey 
     * @Description: 获取RSA私钥
     * @param pfxPath    PFX证书路径
     * @param pwd        PFX证书密码
     * @return
     * @throws Exception
     * @return Key
     */
    public Key getRSAPriKey(String pfxPath, String pwd) throws Exception {
        Key priKey = KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        return priKey;
    }

    /**
     * @Title: getRSAPubKey 
     * @Description: 获取ＲＳＡ公钥
     * @param cerPath   CER证书路径
     * @throws Exception
     * @return Key
     */
    public Key getRSAPubKey(String cerPath) throws Exception {
        X509Cert cert = X509CertHelper.parse(cerPath);
        Key pubKey = cert.getPublicKey();
        return pubKey;
    }
    
    /**
     * @Title: rsaFileP1Sign 
     * @Description: RSA文件签名(PKCS#1)
     * @param fileName   要签名的文件名
     * @param pfxPath    PFX证书路径
     * @param pwd        PFX证书密码
     * @param alg        签名算法
     * @throws Exception
     * @return String
     */
    public String rsaFileP1Sign(String fileName, String pfxPath, String pwd, String alg) throws Exception {
        PrivateKey priKey = (PrivateKey) getRSAPriKey(pfxPath, pwd);
        byte[] signData = new SignatureUtil2().p1SignFile(alg, fileName, priKey, session);
        return new String(signData);
    }

    /**
     * @Title: rsaFileP1Verify 
     * @Description: RSA文件验签(PKCS#1)
     * @param signData           签名数据串
     * @param srcDataFileName    原文文件
     * @param cerPath            CER证书路径
     * @param alg                签名算法
     * @throws Exception
     * @return boolean
     */
    public boolean rsaFileP1Verify(String signData, String srcDataFileName, String cerPath, String alg) throws Exception {
        PublicKey pubKey = (PublicKey) getRSAPubKey(cerPath);
       return new SignatureUtil2().p1VerifyFile(alg, srcDataFileName, signData.getBytes(), pubKey, session);
    }
    
    /**
     * @Title: rsaP1Verify 
     * @Description: RSA消息验签(PKCS#1)
     * @param signData       签名串
     * @param srcData        原文
     * @param alg            签名算法
     * @param cerPath        CER证书路径
     * @throws Exception
     * @return boolean
     */
    public boolean rsaP1Verify(String signData, String srcData, String alg, String cerPath) throws Exception {
        PublicKey pubKey = (PublicKey) getRSAPubKey(cerPath);
        return new SignatureUtil2().p1VerifyMessage(alg, srcData.getBytes("UTF8"), signData.getBytes(), pubKey, session);
    }

    /**
     * @Title: sm2RawSign 
     * @Description: SM2签名(裸签)
     * @param sm2pfxPath     SM2证书路径
     * @param pwd            SM2证书密码
     * @param srcData        原文
     * @throws Exception
     * @return String
     */
    public String sm2RawSign(String sm2pfxPath, String pwd, String srcData) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd);
        byte[] signature = null;
        signature = new SignatureUtil2().p1SignMessage(Mechanism.SM3_SM2, srcData.getBytes("UTF8"), priKey, session);
        return new String(signature);
    }

    /**
     * @Title: sm2RawFileSign 
     * @Description: SM2文件签名(裸签)
     * @param sm2pfxPath       SM2证书路径,如./TestData/sm2pfx.SM2
     * @param pwd              SM2证书密码
     * @param srcDataFileName  原文文件名
     * @throws Exception
     * @return String
     */
    public String sm2RawFileSign(String sm2pfxPath, String pwd, String srcDataFileName) throws Exception {
        /*
         * KeyPair pair = KeyUtil.generateSM2KeyPair(); PrivateKey priKey = pair.getPrivate(); PublicKey pubKey =
         * pair.getPublic();
         */
        PrivateKey priKey = KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd);
        byte[] signDataFileName = new SignatureUtil2().p1SignFile(Mechanism.SM3_SM2, srcDataFileName, priKey, session);
        return new String(signDataFileName);
    }
    
    /**
     * @Title: sm2RawFileVerify 
     * @Description: SM2文件验签(裸签)
     * @param cerPath           CER证书路径,如./TestData/sm2pfx.cer
     * @param signData          签名数据串
     * @param srcDataFilePath   原文文件路径
     * @throws Exception
     * @return boolean
     */
    public boolean sm2RawFileVerify(String cerPath, String signData, String srcDataFilePath) throws Exception {
        X509Cert cert = X509CertHelper.parse(cerPath);
        PublicKey pubKey = cert.getPublicKey();
        return new SignatureUtil2().p1VerifyFile(Mechanism.SM3_SM2, srcDataFilePath, signData.getBytes(), pubKey, session);
    }

    /**
     * @Title: sm2RawVerify 
     * @Description: SM2验签(裸签)
     * @param cerPath      CER证书路径
     * @param signData     签名串
     * @param srcData      原文
     * @throws Exception
     * @return boolean
     */
    public boolean sm2RawVerify(String cerPath, String signData, String srcData) throws Exception {
        X509Cert cert = X509CertHelper.parse(cerPath);
        PublicKey pubKey = cert.getPublicKey();
        byte[] signature = signData.getBytes();
        return new SignatureUtil2().p1VerifyMessage(Mechanism.SM3_SM2, srcData.getBytes("UTF8"), signature, pubKey, session);
    }

    /**
     * @Title: rsaP7SignAttach 
     * @Description: RSA消息签名(PKCS#7,含原文)
     * @param pfxPath      PFX证书路径
     * @param pwd          PFX证书密码
     * @param srcData      原文
     * @param alg          签名算法
     * @throws Exception
     * @return String
     */
    public String rsaP7SignAttach(String pfxPath, String pwd, String srcData, String alg) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        SignatureUtil2 signUtil = new SignatureUtil2();
        byte[] signature = signUtil.p7SignMessageAttach(alg, srcData.getBytes("UTF8"), priKey, cert, session);
        if (signature != null) {
            return new String(signature);
        }
        return null;
    }

    /**
     * @Title: rsaP7FileSignAttach 
     * @Description: RSA文件签名(PKCS#7,含原文)
     * @param pfxPath           PFX证书路径
     * @param pwd               PFX证书密码
     * @param srcDataFilePath   原文件路径
     * @param signDataFilePath  签名文件路径
     * @param alg               签名算法
     * @throws Exception
     * @return String
     */
    public String rsaP7FileSignAttach(String pfxPath, String pwd, String srcDataFilePath, String signDataFilePath, String alg) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        SignatureUtil2 signUtil = new SignatureUtil2();
        signUtil.p7SignFileAttach(alg, srcDataFilePath, signDataFilePath, priKey, cert, session);
        return signDataFilePath;
    }

    /**
     * @Title: rsaP7FileVerifyAttach 
     * @Description: RSA文件验签(PKCS#7,含原文)
     * @param signDataFilePath     签名文件
     * @param sourceFilePath       原文保存路径,不想保存原文，请直接输入null
     * @return String
     */
    public String rsaP7FileVerifyAttach(String signDataFilePath, String sourceFilePath) throws Exception {
        if (sourceFilePath.equals("null"))
            sourceFilePath = null;
        SignatureUtil2 signUtil = new SignatureUtil2();
        if (signUtil.p7VerifyFileAttach(signDataFilePath, sourceFilePath, session)) {
            System.out.println("yes,p7 file attach verify pass");
            X509Cert signCert = signUtil.getSignerCert();
            //签名者证书DN
            return signCert.getSubject();
        } else {
            System.out.println("no,p7 file attch verify failed");
        }
        return null;
    }

    /**
     * @Title: rsaP7VerifyAttach 
     * @Description: RSA消息验签(PKCS#7,含原文)
     * @param signData    签名串
     * @throws Exception
     * @return boolean
     */
    public boolean rsaP7VerifyAttach(String signData) throws Exception {
        return new SignatureUtil2().p7VerifyMessageAttach(signData.getBytes(), session);
    }

    /**
     * @Title: rsaP7FileSignDettach 
     * @Description: RSA文件签名(PKCS#7,不含原文)
     * @param pfxPath          PFX证书路径
     * @param pwd              PFX证书密码
     * @param srcDataFilePath  原文文件名
     * @param alg              签名算法(类头有静态变量)
     * @throws Exception
     * @return String
     */
    public String rsaP7FileSignDettach(String pfxPath, String pwd, String srcDataFilePath, String alg) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        byte[] signData = new SignatureUtil2().p7SignFileDetach(alg, srcDataFilePath, priKey, cert, session);
        return new String(signData);
    }

    /**
     * @Title: rsaP7SignDettach 
     * @Description: RSA消息签名(PKCS#7,不含原文)
     * @param pfxPath    PFX证书路径
     * @param pwd        PFX证书密码
     * @param srcData    原文
     * @param alg        签名算法(类头静态变量)
     * @throws Exception
     * @return String
     */
    public String rsaP7SignDettach(String pfxPath, String pwd, String srcData, String alg) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        byte[] signature = new SignatureUtil2().p7SignMessageDetach(alg, srcData.getBytes("UTF8"), priKey, cert, session);
        return new String(signature);
    }
    
    /**
     * @Title: rsaP7FileVerifyDettach 
     * @Description: RSA文件验签(PKCS#7,不含原文)
     * @param signData         签名数据串
     * @param srcDataFilePath  原文文件路径
     * @throws Exception
     * @return boolean
     */
    public boolean rsaP7FileVerifyDettach(String signData, String srcDataFilePath) throws Exception {
       return new SignatureUtil2().p7VerifyFileDetach(srcDataFilePath, signData.getBytes(), session);
    }

    /**
     * @Title: rsaP7VerifyDettach 
     * @Description: RSA消息验签(PKCS#7,不含原文)
     * @param signData     签名串
     * @param srcData      原文
     * @throws Exception
     * @return boolean
     */
    public boolean rsaP7VerifyDettach(String signData, String srcData) throws Exception {
       return new SignatureUtil2().p7VerifyMessageDetach(srcData.getBytes("UTF8"), signData.getBytes(), session);
    }

    /**
     * @Title: sm2FileSignAttach 
     * @Description: sm2文件签名(含原文)
     * @param sm2pfxPath        SM2证书路径,如./TestData/sm2pfx.SM2
     * @param pwd               SM2证书密码
     * @param srcDataFilePath   原文文件路径
     * @param signDataFilePath  签名文件路径
     * @throws Exception
     * @return String
     */
    public String sm2FileSignAttach(String sm2pfxPath, String pwd, String srcDataFilePath, String signDataFilePath) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromSM2(sm2pfxPath);
        new SignatureUtil2().p7SignFileAttach(Mechanism.SM3_SM2, srcDataFilePath, signDataFilePath, priKey, cert, session);
        //签名文件路径
        return signDataFilePath;
    }

    /**
     * @Title: sm2SignAttach 
     * @Description: sm2消息签名(含原文)
     * @param sm2pfxPath   SM2证书路径,如./TestData/sm2pfx.SM2
     * @param pwd          SM2证书密码
     * @param srcData      原文
     * @throws Exception
     * @return String
     */
    public String sm2SignAttach(String sm2pfxPath, String pwd, String srcData) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromSM2(sm2pfxPath);
        byte[] signature = null;
        signature = new SignatureUtil2().p7SignMessageAttach(Mechanism.SM3_SM2, srcData.getBytes("UTF8"), priKey, cert, session);
        return new String(signature);
    }

    /**
     * @Title: sm2FileVerifyAttach 
     * @Description: sm2文件验签(含原文)
     * @param signDataFilePath  待验证签名文件路径
     * @param sourceFilePath    原文保存路径,不想保存原文，请直接传null
     * @throws Exception
     * @return boolean
     */
    public boolean sm2FileVerifyAttach(String signDataFilePath, String sourceFilePath) throws Exception {
        return new SignatureUtil2().p7VerifyFileAttach(signDataFilePath, sourceFilePath, session);
    }
    
    /**
     * @Title: sm2VerifyAttach 
     * @Description: sm2消息验签(含原文)
     * @param signData     待验证签串
     * @throws Exception
     * @return boolean
     */
    public boolean sm2VerifyAttach(String signData) throws Exception {
        return new SignatureUtil2().p7VerifyMessageAttach(signData.getBytes(), session);
    }

    /**
     * @Title: sm2FileSignDettach 
     * @Description: sm2文件签名(不含原文)
     * @param sm2pfxPath        SM2证书路径,如./TestData/sm2pfx.SM2
     * @param pwd               SM2证书密码
     * @param srcDataFilePath   原文文件路径
     * @throws Exception
     * @return String
     */
    public String sm2FileSignDettach(String sm2pfxPath, String pwd, String srcDataFilePath) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromSM2(sm2pfxPath);
        byte[] signData = new SignatureUtil2().p7SignFileDetach(Mechanism.SM3_SM2, srcDataFilePath, priKey, cert, session);
        return new String(signData);
    }

    /**
     * @Title: sm2SignDettach 
     * @Description: sm2消息签名(不含原文)
     * @param sm2pfxPath   SM2证书路径,如./TestData/sm2pfx.SM2
     * @param pwd          SM2证书密码
     * @param srcData      原文
     * @throws Exception
     * @return String
     */
    public String sm2SignDettach(String sm2pfxPath, String pwd, String srcData) throws Exception {
        PrivateKey priKey = KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromSM2(sm2pfxPath);
        byte[] signature = null;
        signature = new SignatureUtil2().p7SignMessageDetach(Mechanism.SM3_SM2, srcData.getBytes("UTF8"), priKey, cert, session);
        return new String(signature);
    }

    /**
     * @Title: sm2FileVerifyDettach 
     * @Description: sm2文件验签(不含原文)
     * @param signData          待验证签名数据
     * @param srcDataFilePath   原文文件路径
     * @throws Exception
     * @return void
     */
    public boolean sm2FileVerifyDettach(String signData, String srcDataFilePath) throws Exception {
        return new SignatureUtil2().p7VerifyFileDetach(srcDataFilePath, signData.getBytes(), session);
    }

    /**
     * @Title: sm2VerifyDettach 
     * @Description: sm2消息验签(不含原文)
     * @param signData   待验证签名
     * @param srcData    原文
     * @throws Exception
     * @return boolean
     */
    public boolean sm2VerifyDettach(String signData, String srcData) throws Exception {
       return new SignatureUtil2().p7VerifyMessageDetach(srcData.getBytes("UTF8"), signData.getBytes(), session);
    }

    /**
     * @Title: envelopMessage 
     * @Description: PKCS#7消息加密(数字信封)
     * @param cerPath               接收者证书路径，多张证书用；分开
     * @param content               原文
     * @param symmetricAlgorithm    密云加密解密算法(类头静态变量)
     * @throws Exception
     * @return String
     */
    public String envelopMessage(String cerPath, String content, String symmetricAlgorithm) throws Exception {
        String[] certPaths = cerPath.split(";");
        X509Cert[] certs = new X509Cert[certPaths.length];
        for (int i = 0; i < certPaths.length; i++) {
            X509Cert cert = new X509Cert(new FileInputStream(certPaths[i]));
            certs[i] = cert;
        }
        byte[] encryptedData = EnvelopeUtil.envelopeMessage(content.getBytes("UTF8"), symmetricAlgorithm, certs);
        return new String(encryptedData);
    }

    /**
     * @Title: sm2EnvelopMessage 
     * @Description: SM2消息加密(数字信封)
     * @param cerPath     接收者证书路径，多张证书用；分开
     * @param content     原文
     * @throws Exception
     * @return String
     */
    public String sm2EnvelopMessage(String cerPath, String content) throws Exception {
        String[] certPaths = cerPath.split(";");
        X509Cert[] certs = new X509Cert[certPaths.length];
        for (int i = 0; i < certPaths.length; i++) {
            X509Cert cert = new X509Cert(new FileInputStream(certPaths[i]));
            certs[i] = cert;
        }
        byte[] encryptedData = EnvelopeUtil.envelopeMessage(content.getBytes("UTF8"), SYMMETRICALGORITHM_SM4_CBC, certs);
        return new String(encryptedData);
    }

    /**
     * @Title: openSM2EnvelopMessage 
     * @Description: SM2消息解密(数字信封)
     * @param encryptedData   接收到的数字信封数据
     * @param sm2Path         SM2证书路径
     * @param pwd             SM2证书密码
     * @throws Exception
     * @return String
     */
    public String openSM2EnvelopMessage(String encryptedData, String sm2Path, String pwd) throws Exception {
        PrivateKey priKey = (PrivateKey) KeyUtil.getPrivateKeyFromSM2(sm2Path, pwd);
        X509Cert cert = CertUtil.getCertFromSM2(sm2Path);
        byte[] sourceData = EnvelopeUtil.openEvelopedMessage(encryptedData.getBytes(), priKey, cert,session);
        return new String(sourceData, "UTF8");
    }

    /**
     * @Title: sm2EnvelopFile 
     * @Description: SM2文件加密(数字信封) 
     * @param cer              接收者的CER证书
     * @param sourceFilePath   source文件路径
     * @param encryptFilePath  加密后文件路径
     * @throws Exception
     * @return String
     */
    public String sm2EnvelopFile(String cer, String sourceFilePath, String encryptFilePath) throws Exception {
        FileInputStream fis = new FileInputStream(cer);
        X509Cert cert = new X509Cert(fis);
        X509Cert[] certs = new X509Cert[1];
        certs[0] = cert;
        EnvelopeUtil.envelopeFile(sourceFilePath, encryptFilePath, SYMMETRICALGORITHM_SM4_CBC, certs);
        return encryptFilePath;
    }

    /**
     * @Title: openSM2EnvelopFile 
     * @Description: SM2文件解密(数字信封)
     * @param encryptFilePath    加密后文件路径
     * @param plainTextFilePath  解密后文件路径
     * @param sm2pfxPath         SM2证书路径
     * @param pwd                SM2证书密码
     * @throws Exception
     * @return String
     */
    public String openSM2EnvelopFile(String encryptFilePath, String plainTextFilePath, String sm2pfxPath, String pwd) throws Exception {
        PrivateKey priKey = (PrivateKey) KeyUtil.getPrivateKeyFromSM2(sm2pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromSM2(sm2pfxPath);
        EnvelopeUtil.openEnvelopedFile(encryptFilePath, plainTextFilePath, priKey, cert,session);
        return plainTextFilePath;
    }

    
    /**
     * @Title: encryptMessage_3DES 
     * @Description: 3DES消息加密
     * @param content      原文
     * @param pwd          密钥 比如:0282010100ea6226b38463db95bf4f0cae06b5c8ffa73bc3bdb83c193082010a
     * @throws Exception
     * @return String
     */
    public String encryptMessage_3DES(String content, String pwd) throws Exception {
        return EncryptUtil.encryptMessageByPwd_3DES(content, pwd);
    }
    
    /**
     * @Title: decryptMessage_3DES 
     * @Description: 3DES消息解密
     * @param content     密文
     * @param pwd         密钥 比如:0282010100ea6226b38463db95bf4f0cae06b5c8ffa73bc3bdb83c193082010a
     * @throws Exception
     * @return String
     */
    public String decryptMessage_3DES(String content, String pwd) throws Exception {
        return EncryptUtil.decryptMessageByPwd_3DES(content, pwd);
    }
    
    
    /**
     * @Title: encryptFile_3DES 
     * @Description: 3DES文件加密
     * @param srcFilePath   原文件路径
     * @param saveFilePath  加密后的文件保存路径
     * @param pwd           密钥 比如:0282010100ea6226b38463db95bf4f0cae06b5c8ffa73bc3bdb83c193082010a
     * @throws Exception
     * @return void
     */
    public String encryptFile_3DES(String srcFilePath, String saveFilePath, String pwd) throws Exception {
        EncryptUtil.encryptFileByPwd_3DES(srcFilePath, saveFilePath, pwd);
        return saveFilePath;
    }
    
    /**
     * @Title: decryptFile_3DES 
     * @Description: 3DES文件解密
     * @param encryptFilePath    加密文件路径
     * @param plainTextFilePath  解密后文件保存路径
     * @param pwd                密钥 比如:0282010100ea6226b38463db95bf4f0cae06b5c8ffa73bc3bdb83c193082010a
     * @throws Exception
     * @return String
     */
    public String decryptFile_3DES(String encryptFilePath, String plainTextFilePath, String pwd) throws Exception {
        EncryptUtil.decryptFileByPwd_3DES(encryptFilePath, plainTextFilePath, pwd);
        return plainTextFilePath;
    }
    
    /**
     * @Title: openMessage 
     * @Description: 消息解密(数字信封) 
     * @param encryptedData  接收到的数字信封数据
     * @param pfxPath        PFX证书路径
     * @param pwd            PFX证书密码
     * @throws Exception
     * @return String
     */
    public String openMessage(String encryptedData, String pfxPath, String pwd) throws Exception {
        PrivateKey priKey = (PrivateKey) KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        byte[] sourceData = EnvelopeUtil.openEvelopedMessage(encryptedData.getBytes(), priKey, cert,session);
        return new String(sourceData, "UTF8");
    }

    /**
     * @Title: envelopFile 
     * @Description: 文件加密(数字信封)
     * @param tt                    接收者的证书
     * @param sourceFilePath        source文件路径
     * @param encryptFilePath       加密后文件路径
     * @param symmetricAlgorithm    密云加密解密算法(类头静态变量)
     * @throws Exception
     * @return String
     */
    public String envelopFile(String tt, String sourceFilePath, String encryptFilePath, String symmetricAlgorithm) throws Exception {
        FileInputStream fis = new FileInputStream(tt);
        X509Cert cert = new X509Cert(fis);
        X509Cert[] certs = new X509Cert[1];
        certs[0] = cert;
        EnvelopeUtil.envelopeFile(sourceFilePath, encryptFilePath, symmetricAlgorithm, certs);
        fis = new FileInputStream(encryptFilePath);
        byte[] encryptData = new byte[fis.available()];
        fis.read(encryptData);
        fis.close();
        return encryptFilePath;
    }

    /**
     * @Title: openFile 
     * @Description: 文件解密(数字信封)
     * @param encryptFilePath    加密后文件路径
     * @param plainTextFilePath  解密后文件路径
     * @param pfxPath            PFX证书路径
     * @param pwd                PFX证书密码
     * @throws Exception
     * @return String
     */
    public String openFile(String encryptFilePath, String plainTextFilePath, String pfxPath, String pwd) throws Exception {
        PrivateKey priKey = (PrivateKey) KeyUtil.getPrivateKeyFromPFX(pfxPath, pwd);
        X509Cert cert = CertUtil.getCertFromPfx(pfxPath, pwd);
        EnvelopeUtil.openEnvelopedFile(encryptFilePath, plainTextFilePath, priKey, cert,session);
        return plainTextFilePath;
    }
    
    /**
     * @Title: cithperMessageByKey 
     * @Description: 文件加密
     * @param src           待加密的原文
     * @param keyType       产生密钥算法(类头静态变量)
     * @throws Exception
     * @return void
     */
    public void cithperMessageByKey(String src, String keyType) throws Exception{
        Key key = KeyUtil.generateKey(new Mechanism(keyType), session);
        Mechanism mechanism = null;
        if(keyType.equals(Mechanism.DES3_KEY)){
            CBCParam param = new CBCParam();
            byte[] iv = new byte[8];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            param.setIv(iv);
            mechanism = new Mechanism(Mechanism.DES3_CBC);
            mechanism.setParam(param);
        }else if(keyType.equals(Mechanism.SM4_KEY)){
            CBCParam param = new CBCParam();
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            param.setIv(iv);
            mechanism = new Mechanism(Mechanism.SM4_CBC);
            mechanism.setParam(param);
        }else if(keyType.equals(Mechanism.RC4_KEY)){
            mechanism = new Mechanism(Mechanism.RC4);
        }
        byte[] encryptMessage = EncryptUtil.encrypt(mechanism, key, src.getBytes("UTF-8"), session); 
        System.out.println("加密后的密文为：" + new String(encryptMessage,"UTF-8"));
        byte[] plainText = EncryptUtil.decrypt(mechanism, key, encryptMessage, session);
        System.out.println("解密后的明文为：" + new String(plainText,"UTF-8"));
    }
    
    /**
     * @Title: cithperFileByKey 
     * @Description: 文件加密
     * @param srcFilePath   待加密的原文件路径
     * @param keyType       产生密钥算法(类头静态变量)
     * @throws Exception
     * @return void
     */
    public void cithperFileByKey(String srcFilePath, String keyType) throws Exception {
        Key key = KeyUtil.generateKey(new Mechanism(keyType), session);
        Mechanism mechanism = null;
        if(keyType.equals(Mechanism.DES3_KEY)){
            CBCParam param = new CBCParam();
            byte[] iv = new byte[8];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            param.setIv(iv);
            mechanism = new Mechanism(Mechanism.DES3_CBC);
            mechanism.setParam(param);
        }else if(keyType.equals(Mechanism.SM4_KEY)){
            CBCParam param = new CBCParam();
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            param.setIv(iv);
            mechanism = new Mechanism(Mechanism.SM4_CBC);
            mechanism.setParam(param);
        }else if(keyType.equals(Mechanism.RC4_KEY)){
            mechanism = new Mechanism(Mechanism.RC4);
        }
        
        EncryptUtil.encrypt(mechanism, key, srcFilePath, srcFilePath + ".enc", session); 
        System.out.println("加密后的文件为：" + srcFilePath + ".enc");
        EncryptUtil.decrypt(mechanism, key, srcFilePath + ".enc", srcFilePath + ".dec", session);
        System.out.println("解密后的文件为：" + srcFilePath + ".dec");
    }
}
