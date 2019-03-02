package com.eb.dianlianbao_server.util;

public interface MarkedCaption {
	enum Parameter implements MarkedCaption{  
		参数缺失, 参数错误, get_success, 手机号为空 ,经纬度不能为空,参数异常,所选区域地址错误,推荐码不存在,推广码不存在,邀请码不存在,验证成功,系统未设置,登录密码为空,支付密码为空,绑定新手机号成功
    }  
    enum Login implements MarkedCaption{  
    	账号或密码错误, 登录失败, 登录成功, 用户不存在, 该账号已冻结, 请登录后查看, 请登录后进行操作 ,非商家用户,非用户端用户,该账号未绑定手机号,该账号绑定的手机号不一致,已有用户绑定该手机号 
    } 
    enum Regist implements MarkedCaption{  
    	注册成功, 注册失败, 成功登录, 用户不存在  
    } 
    enum ShopParam implements MarkedCaption{  
    	入驻申请成功,店铺所属拥护者不一致,店铺不存在,店铺审核通过才能操作
    } 
    enum GoodsParam implements MarkedCaption{  
    	商品发布成功, 商品发布失败,商品修改成功,商品修改失败,商品不存在,非该商户商品
    } 
    enum updateOperat implements MarkedCaption{  
    	删除成功, 删除失败, 修改成功, 修改失败, 设置成功, 设置失败, 操作成功, 操作失败,提交成功,提交失败
    }
    enum recharge implements MarkedCaption{  
    	充值成功, 充值失败, 点赞成功, 点赞失败, 收藏成功, 收藏失败, 评论成功
    } 
    enum giveOperat implements MarkedCaption{  
    	点赞成功, 点赞失败
    } 
    enum collectOperat implements MarkedCaption{  
    	收藏成功, 收藏失败
    } 
    enum replyOperat implements MarkedCaption{  
    	评论成功,评论失败,回复成功, 回复失败,评价成功, 评价失败
    } 
    enum uploadOperat implements MarkedCaption{  
    	上传成功,上传失败
    } 
    enum JSON_MSG implements MarkedCaption{  
    	JSON格式错误, JSON参数缺失 ,JSON参数缺失转换异常
    }
    enum Order_MSG implements MarkedCaption{  
    	订单不存在,订单非待发货状态,订单非待确认状态,订单非待收货状态,订单非待退款状态
    }
    enum Shop_MSG implements MarkedCaption{  
    	店铺审核已通过,店铺认证请求已提交,店铺认证申请提交成功,店铺所在地区无对应地区记录,系统未设置入驻减免费用,入驻减免费用设置金额错误,入驻费用错误,入驻失败
    }
    enum Bank_MSG implements MarkedCaption{  
    	添加成功,删除成功,银行卡不存在,银行卡已存在
    }
    enum Pwd_MSG implements MarkedCaption{  
    	登录密码设置成功,支付密码设置成功
    }
    enum CODE_MSG implements MarkedCaption{  
    	验证码错误
    }
    
}
