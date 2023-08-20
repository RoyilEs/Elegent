package com.chatroy.royai.MsgOrImg;

import love.forte.simbot.resources.PathResource;
import love.forte.simbot.resources.Resource;

import java.nio.file.Path;

public interface messageOrImg {
     String[] MSG = {"大吉","中吉","小吉","吉","大凶","中凶","小凶","凶"};
     PathResource[] IMG_AI =
                    {Resource.of(Path.of("img/1.png")),
                    Resource.of(Path.of("img/3.png")),
                    Resource.of(Path.of("img/4.png")),
                    Resource.of(Path.of("img/5.png")),
                    Resource.of(Path.of("img/6.png")),
                    Resource.of(Path.of("img/7.png")),
                    Resource.of(Path.of("img/8.png")),
                    Resource.of(Path.of("img/9.png")),
                    Resource.of(Path.of("img/bot.png")),
                    Resource.of(Path.of("img/123.png")),
                    Resource.of(Path.of("img/13.jpg")),
                    Resource.of(Path.of("img/14.png")),
                    Resource.of(Path.of("img/15.png")),
                    Resource.of(Path.of("img/114.png")),
                    Resource.of(Path.of("img/16.png")),
                    Resource.of(Path.of("img/17.png")),
                    Resource.of(Path.of("img/18.png")),
                    Resource.of(Path.of("img/19.png")),
                    Resource.of(Path.of("img/20.png")),
                    Resource.of(Path.of("img/21.png")),
            };

     PathResource[] IMG =
                    {Resource.of(Path.of("img/10.jpg")),
                     Resource.of(Path.of("img/11.jpg")),
                     Resource.of(Path.of("img/12.jpg")),

             };

    String[] test = {"test","查询bot状态","テスト","测试"};
    String[] testMsg = {
            "test","你测试尼玛呢","你在测试谁","我是个homo","丁真",
            "测个锤子试，老子在正常工作",
            "你是在质疑机器人的稳定性吗？",
            "test你个头成天就知道测试无不无聊",
            "与其在这浪费时间测试还不如现在就出道去当vtb",
            "我求你别浪费时间在这测试老子了行吗",
            "测试失败，请现在立刻重启您的设备",
            "你咋不测试一下是不是你自己有问题",
            "运行超时",
            "烫烫烫烫烫烫",
            "到底是你在测试我还是我在测试你",
            "当你测试bot的时候，bot也在测试着你",
            "return null;",
            "已将测试结果转发给Roy",
            "很快就到你家门口",
            "正在执行测试指令sudo rm -rf /*",
            "hello world",
            "测试成功，请缴纳测试费用114514元",
            "sudo pkill -9 Roy1Bot",
            "Uncaught ReferenceError: test is not defined",
            "program exited with code 114514",
            "0x0001BF52 指令引用了 0x001D4B42 内存。 该内存不能为 read。",
            "/kill @s",
            "Error(code=45, title=禁止登陆,\nmessage=你当前使用的QQ版本过低)"};


}