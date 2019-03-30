<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- stylesheets -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/Treant.css" type="text/css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- javascript -->
<script src="${pageContext.request.contextPath}/raphael.js"></script>
<script src="${pageContext.request.contextPath}/Treant.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>

<style>
	body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td { 
		margin:0; padding:0; 
	}
	table { border-collapse:collapse; border-spacing:0; }
	fieldset,img { border:0; }
	address,caption,cite,code,dfn,em,strong,th,var { font-style:normal; font-weight:normal; }
	caption,th { text-align:left; }
	h1,h2,h3,h4,h5,h6 { font-size:100%; font-weight:normal; }
	q:before,q:after { content:''; }
	abbr,acronym { border:0; }
	
	body { background: #fff; }
	/* optional Container STYLES */
	.chart { height: 600px; margin: 5px; width: 900px; }
	.Treant > .node { padding: 3px; border: 1px solid #484848; border-radius: 3px; }
	.Treant > .node img { width: 100%; height: 100%; }
	
	.Treant .collapse-switch { width: 100%; height: 100%; border: none; }
	.Treant .node.collapsed { background-color: #DEF82D; }
	.Treant .node.collapsed .collapse-switch { background: none; }
</style>

</head>
<body>

    <div class="chart" id="collapsable-example"></div>

	<script>
	
		$(document).ready(function(){
			$.ajax({
                type: "GET",
                url: "IndexController",
                data: "",
                dataType: "text",
                success: function(result){
                	console.log(result);
                	var simple_chart_config = $.parseJSON(result);
                	console.log(simple_chart_config);
                	var chart = new Treant(simple_chart_config, function() { alert( 'Tree Loaded' ); }, $ );
                },
                error: function(e) {
                	console.log("Error");
                }
            });
    	});    		
	
	</script>
</body>
</html>