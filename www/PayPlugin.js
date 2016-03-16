/**
*
* 这是注释
*/

var exec = require('cordova/exec');

var PayPlugin = {
	
    pay:function(charge,success, error) {
    	if(!$.os.ios){
			exec(success, error, "PayPlugin", "weixinPay", [charge]);
    	}else
    	{
	        cordova.exec(success, error, "PayPlugin", "createPayment", [charge]);
    	}
        
    }
};
module.exports = PayPlugin;