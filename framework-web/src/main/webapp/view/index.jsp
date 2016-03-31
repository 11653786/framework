<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2015/8/11
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head >
    <title>22</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/angularjs/angular.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/style.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/style.css">--%>
    <style type="text/css">
      .test1{
        font-size: 20px;
      }
    </style>
  </head>
  <!--angularjs取件-->
  <body ng-app="myapp">
<!--angularjs el表达式-->
  <div ng-controller="testController">
  <input type="text" name="username1" ng-model="username" /><br>
 <input type="text" name="password" ng-model="password" />
    <!--绑定,绑定了model username,一样的值啊-->
  <div ng-bind="username"></div>
    fullName()<br>
    {{fullName()}}
  </div>
  form表单<br>
  <form ng-controller="form1" name="form1" id="form1" >
    <input type="text" ng-model="users.username1" />
    <input type="text" ng-model="users.password1" />
  <input type="text" ng-model="name"    />
    <!--提交表单测试-->
    <input type="button" ng-click="add1()" value="add1()" />
    <div id="id1" ng-model="div1">哈哈哈</div>
  </form>
  </div>



  </body>
</html>
<script type="text/javascript">
  //初始化angularjs
  var app=angular.module('myapp',[]);
  app.controller('testController',function($scope){
      //给testController中的model复制
        $scope.username='账号';
        $scope.password="密码";
        //给{{fullName}}赋值
        $scope.fullName=function(){
          return $scope.username+","+$scope.password;
        };
  });

  //form表单
  app.controller('form1',function($scope){
      //给form表单中赋值,给form表单中的users中的username1赋值
    //1.给ng-model=的user的username1对象赋值
    $scope.users={username1:"hehe"};
    //使用copy,也可以当成是赋值
   // alert("1!=2: "+angular.equals('1','2'));
    //2.onclick事件,angulargs的onclick事件
    $scope.add1=function(){
      if( $scope.name==undefined ||  $scope.name==null ||  $scope.name==''){
            alert('请输入值啊');
             return false;
      }else{
        $scope.name +=1;
      }
    }
    var bodys=$("body");
    //3.elelment,给当前对象添加jquery方法
  angular.element(bodys).addClass('test1');
    //4.angulr.forEach,对一个对象做循环操作
    var objs =[{a:1},{b:2}];
    angular.forEach(objs, function(data,index,array){
    //data等价于array[index]
      //循环输出这个对象 array[index]就是objs里面的对象
            for(var c in array[index]){
                alert(c+","+array[index][c]);
            }
    });

    //5.json字符串转json对象?
    var strUser = {firstName:"fox",lastName:"zhang"};
    var objUser = angular.fromJson(strUser);
    alert(objUser.firstName);
   //6.返回这个函数的第一个参数
    var index0=angular.identity([1,2],"haha");
    alert("index0: "+index0);
    //
  });

</script>
