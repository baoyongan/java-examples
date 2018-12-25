密码都是：123456
C:\Users\bqct_bya>keytool -genkey -validity 36000 -alias www.bya.vip -keyalg RSA -keystore D:\keys\bya.keystore
输入密钥库口令:
再次输入新口令:
您的名字与姓氏是什么?
  [Unknown]:  www.bya.vip
您的组织单位名称是什么?
  [Unknown]:  bqct
您的组织名称是什么?
  [Unknown]:  bqct
您所在的城市或区域名称是什么?
  [Unknown]:  BJ
您所在的省/市/自治区名称是什么?
  [Unknown]:  BJ
该单位的双字母国家/地区代码是什么?
  [Unknown]:  CN
CN=www.bya.vip, OU=bqct, O=bqct, L=BJ, ST=BJ, C=CN是否正确?
  [否]:  Y

输入 <www.bya.vip> 的密钥口令
        (如果和密钥库口令相同, 按回车):

其中 
-genkey表示生成密钥 
-validity指定证书有效期，这里是36000天 
-alias指定别名，这里是www.zlex.org 
-keyalg指定算法，这里是RSA 
-keystore指定存储位置，这里是d:\zlex.keystore 

在这里我使用的密码为 123456 
2.生成自签名证书 
光有keyStore文件是不够的，还需要证书文件，证书才是直接提供给外界使用的公钥凭证。 
导出证书： 
C:\Users\bqct_bya>keytool -export -keystore D:\keys\bya.keystore -alias www.bya.vip -file D:\keys\bya.cer -rfc
输入密钥库口令:
存储在文件 <D:\keys\bya.cer> 中的证书

其中 
-export指定为导出操作 
-keystore指定keystore文件 
-alias指定导出keystore文件中的别名 
-file指向导出路径 
-rfc以文本格式输出，也就是以BASE64编码输出 
这里的密码是 123456 