package cn.yfjz.core.global;

public abstract class ISecurityConst {
	
	/** 使用jwt进行单点登录的密钥 */
	public static final String SSO_JWT_SECURITY_KEY = "jwt.token.$%^&%$_-$%%$12qazw2sxQAZX00004";

	public static enum UserType{
		USER(1),
		TEACHER(2),
		PARENT(3),
		STUDENT(4);
		
		private int flag;
		UserType(int flag) {
			this.flag = flag;
		}
		
		public int getFlag() {
			return this.flag;
		}
		
		public String getName() {
			return this.name().toLowerCase();
		}
		
		public static String getName(int flag) {
			for (UserType it : UserType.values()) {
				if(it.getFlag() == flag) {
					return it.getName();
				}
			}
			return UserType.USER.getName();
		}
		
	}
	
}
