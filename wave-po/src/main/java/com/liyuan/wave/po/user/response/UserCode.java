package com.liyuan.wave.po.user.response;

import com.google.common.collect.ImmutableMap;
import com.langchao.wavecommon.exception.ExceptionResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@ToString
public enum UserCode implements ExceptionResult {
    USER_DENIED_DELETE(false,31001,"删除用户失败！"),
    USER_PUBLISH_PERVIEWISNULL(false,31002,"还没有进行用户预览！"),
    USER_PUBLISH_CDETAILERROR(false,31003,"创建用户详情页面出错！"),
    USER_PUBLISH_ERROR(false,31006,"发布用户出错！"),
    USER_PUBLISH_USERIDISNULL(false,31004,"用户Id为空！"),
    USER_PUBLISH_VIEWERROR(false,31005,"发布用户视图出错！"),
    USER_MEDIS_URLISNULL(false,31101,"选择的媒资文件访问地址为空！"),
    USER_MEDIS_NAMEISNULL(false,31102,"选择的媒资文件名称为空！"),
    USER_GET_NOTEXISTS(false,31102,"选择的用户不存在！"),
    USER_MEDIA_TEACHPLAN_GRADEERROR(false,31102,"允许选择第三级的用户计划关联视频！"),
    USER_PIC_EXISTS(false,31103,"用户图片已存在！"),
    USER_PREVIEW_ERROR(false,31104,"页面预览异常！"),
    USER_GENERATEHTML_SAVEHTMLERROR(false,31105,"页面静态化出错！");


    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;

    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private UserCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, UserCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, UserCode> builder = ImmutableMap.builder();
        for (UserCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
