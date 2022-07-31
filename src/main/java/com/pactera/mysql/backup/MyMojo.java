package com.pactera.mysql.backup;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;

@Mojo(name="mysql-backup",defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class MyMojo extends AbstractMojo {

    //数据库类型，目前只支持mysql的
    @Parameter
    private String type;
    //主机
    @Parameter
    private String host;
    //端口号
    @Parameter
    private String port;
    //用户名
    @Parameter
    private String username;
    //密码
    @Parameter
    private String password;
    //导出数据库的数据库名
    @Parameter
    private String exportDatabaseName;



    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System. out.println("******************************************start********************************************************") ;
        Runtime runtime = Runtime.getRuntime();
        //获取导出命令
        String command = getCommand();
        System. out.println("command:" + command) ;
        try {
            //执行导出命令
            Process exec = runtime.exec(command);
        }catch (IOException e){
            e.printStackTrace();
        }
        System. out.println("******************************************end**********************************************************") ;
    }

    public String getCommand(){
        String command = "";
        //mysql
        if("mysql".equals(type)){
            //mysql导出命令为mysqldump -hlocalhost -P3306 -uroot -p123 mydb api_information > ./target/api_information.sql
            //java不支持 > 的操作，因此用 -r代替
            command = "mysqldump -u"+username+" -p"+password+" -h"+host+" -P"+port+" "+exportDatabaseName+" api_information -r ./sql/api_information.sql";
            return command;
        }
        return command;
    }
}
