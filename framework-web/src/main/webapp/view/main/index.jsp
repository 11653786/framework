<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/25 0025
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入自定义标签-->
<%@ include file="/top.jsp" %>
<html>
<head>
    <title>前台首页</title>
    <simple:Script hasJquery="true" hasEasyUi="false" hasAngularjs="false" hasBootStrap="true"
                   hasValid="false" hasYtUtil="false" hasYtResourcesCss="false" hasYtResourcesJs="false"
                   hasYtTreeExtends="false"></simple:Script>
    <%--引入bootstrap扩展的css样式--%>
    <link rel='stylesheet'
          href="${pageContext.request.contextPath}/static/css/bootstrap-extends-yt.css"></link>
    <script type="text/javascript">
        //自动控制轮播图时间
        $(function () {
            $('.carousel').carousel({
                interval: 3000
            })
        });
    </script>
</head>
<body>
<!-- 带下拉框的顶部导航菜单 -->
<div class="collapse navbar-collapse navbar-inverse">
    <!--菜单在右边-->
    <div class="btn  mystyle-btn-bootstrap  btn-danger">注册</div>
    <div class="btn  mystyle-btn-bootstrap  btn-danger">登录</div>
    <ul class="nav navbar-nav">
        <li class="active"><a href="#">选中页面 <span class="sr-only">(current)</span></a></li>
        <li><a href="#">第二页</a></li>
        <!--带下拉框的按钮-->
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false">带图标的下拉菜单
                <!--图标-->
                <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">子菜单1</a></li>
                <li><a href="#">子菜单2</a></li>
                <li><a href="#">子菜单3</a></li>
                <!--实现-->
                <li class="divider"></li>
                <li><a href="#">子菜单4</a></li>
                <li class="divider"></li>
                <li><a href="#">子菜单5</a></li>
            </ul>
        </li>
    </ul>

</div>
<!-- /.navbar-collapse -->
<!--carousel,轮播图样式-->
<!--slide滑动样式,很平滑-->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators,轮播图的小点 -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0"
            class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <!--轮播图的样式只能写在这里,style中用百分比没效果,获取不了图片的高度和宽度-->
            <img src="${pageContext.request.contextPath}/static/images/main/banner1.jpg"
                 style="height:400px;width:100%;" alt="轮播1">

            <div class="carousel-caption">
                <h3>h</h3>

                <div>text</div>
            </div>
        </div>

        <div class="item">
            <img src="${pageContext.request.contextPath}/static/images/main/banner2.jpg"
                 style="height:400px;width:100%;" alt="轮播2">
            <!--可以写文字-->
            <div class="carousel-caption">
            </div>
        </div>

        <div class="item">
            <img src="${pageContext.request.contextPath}/static/images/main/banner3.jpg"
                 style="height:400px;width:100%;" alt="轮播3">

            <div class="carousel-caption">
            </div>
        </div>

        <div class="item">
            <img src="${pageContext.request.contextPath}/static/images/main/banner4.jpg"
                 style="height:400px;width:100%;" alt="轮播3">

            <div class="carousel-caption">
            </div>
        </div>

        <div class="item">
            <img src="${pageContext.request.contextPath}/static/images/main/banner5.jpg"
                 style="height:400px;width:100%;" alt="轮播3">

            <div class="carousel-caption">
                hello,bootstrap!
            </div>
        </div>
    </div>

    <!-- Controls,左右的箭头,用来控制点击箭头切换轮播图片 -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="container">
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a1.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a2.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a3.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a4.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a5.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a6.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a7.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a8.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a9.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a10.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a11.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a12.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a13.png"/>
    <img class="col-lg-6 top" src="${pageContext.request.contextPath}/static/images/main/a14.png"/>
</div>
<footer class="main-footer">
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="widget">
                    <h4 class="title">友情链接</h4>

                    <div class="content friend-links">
                        <a href="http://www.bootcss.com/" title="Bootstrap中文网"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Bootstrap中文网'])" target="_blank">Bootstrap中文网</a>
                        <a href="http://www.golaravel.com/" title="Laravel中文网"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Laravel中文网'])" target="_blank">Laravel中文网</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="widget">
                    <h4 class="title">我们用到的技术</h4>

                    <div class="content tag-cloud">
                        <a href="http://www.bootcss.com/" title="Bootstrap中文网"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Bootstrap中文网'])" target="_blank">Bootstrap</a>
                        <a href="http://www.ghostchina.com/" title="Ghost中国"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Ghost中国'])" target="_blank">Ghost</a>
                        <a href="http://www.bootcdn.cn/" title="BootCDN"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'BootCDN'])" target="_blank">BootCDN</a>
                        <a href="http://www.gruntjs.net/" title="Grunt中文网"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Grunt中文网'])" target="_blank">Grunt</a>
                        <a href="http://www.jquery123.com/" title="jQuery中文文档"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'jQuery中文文档'])"
                           target="_blank">jQuery</a>
                        <a href="http://babeljs.cn/" title="Babeljs中文网"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Babeljs中文网'])"
                           target="_blank">Babeljs</a>
                        <a href="http://lodashjs.com/" title="lodash中文网"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Lodash中文网'])" target="_blank">Lodash</a>
                        <a href="http://www.gulpjs.com.cn/" title="Gulp中文网"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', 'Gulp中文网'])" target="_blank">Gulp</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="widget">
                    <h4 class="title">赞助商</h4>

                    <div class="content friend-links">
                        <a href="https://www.upyun.com/" title="又拍云"
                           onclick="_hmt.push(['_trackEvent', 'link', 'click', '又拍云'])" target="_blank">又拍云</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
<style type="text/css">
    .top {
        margin-top: 10px;
    }

    body {
        background: #255625;
    }

    /*底部的样式*/
    .main-footer {
        background: #202020;
        margin: 1px 0 0;
        color: #959595
    }

    .widget {
        margin-bottom: 35px
    }

    .widget.title {
        margin-top: 0;
        padding-bottom: 7px;
        border-bottom: 1px solid #ebebeb;
        margin-bottom: 21px;
        position: relative
    }

    .widget.title:after {
        content: "";
        width: 90px;
        height: 1px;
        background: #337ab7;
        position: absolute;
        left: 0;
        bottom: -1px
    }

    .widget.friend-linksa {
        display: block;
        border-bottom: 1px dashed #303030;
        padding-bottom: 14px;
        margin-bottom: 14px;
        color: #959595;
        -webkit-transition: all .2s ease;
        -o-transition: all .2s ease;
        transition: all .2s ease
    }

    .widget .friend-linksa:hover {
        color: #fff;
        text-decoration: none
    }

    .widget .friend-linksa:last-child {
        margin-bottom: 0
    }

    .widget .tag-clouda {
        border: 1px solid #ebebeb;
        padding: 2px 7px;
        color: #959595;
        line-height: 1.5em;
        display: inline-block;
        margin: 0 7px 7px 0;
        -webkit-transition: all .2s ease;
        -o-transition: all .2s ease;
        transition: all .2s ease
    }

    .widget .tag-clouda:hover {
        color: #fff;
        background: #337ab7;
        border: 1px solid #337ab7;
        text-decoration: none
    }

    .submit-site {
        background-color: #eee;
        padding-top: 40px;
        padding-bottom: 40px;
        margin-top: 20px
    }

    .main-footer {
        background: #202020;
        padding: 35px 0 0;
        color: #959595
    }

    .main-footer .widget {
        padding: 030px
    }

    .main-footer .widget .title {
        color: #fff;
        border-bottom: 1px solid #303030
    }

    .main-footer .widget .tag-clouda {
        border: 1px solid #303030
    }

    .main-footer .widget .tag-clouda:hover {
        border: 1px solid #337ab7
    }

    .copyright {
        background: #111;
        font-size: 13px;
        text-align: center;
        color: #555;
        padding-top: 14px;
        padding-bottom: 20px;
        border-top: 1px solid #303030
    }

    .copyrightspan {
        margin: 0.5em
    }

    .copyrighta {
        color: #555
    }

    #back-to-top {
        position: fixed;
        right: 10px;
        bottom: 10px;
        background: rgba(51,
        122,
        183,
        0.6);
        color: #fff;
        text-align: center;
        border-radius: 2px;
        z-index: 1;
        display: none
    }

    #back-to-top:hover {
        background: #337ab7
    }

    #back-to-topi {
        width: 30px;
        height: 30px;
        line-height: 30px
    }
</style>