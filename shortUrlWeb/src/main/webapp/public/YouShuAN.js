var path_prefix = getWebPath();
var js_path_prefix =  getWebPath();


YouShuAN = {
	version : '1.0'
};

YouShuAN._import = function(type, path, className, lang) {
	if (type == "js") {
		document.write("<script type='text/javascript' src='" + path_prefix + path + "' " + (lang ? "charset='" + lang + "'" : "") + "></script>");
	} else if (type == "css") {
		document.write("<link type='text/css' rel='stylesheet' href='" + path_prefix + path + "' id='"+className+"'/>");
	} else if (type == "behavior") {
		document.write('<style>' + className + '{behavior:url(' + path_prefix + path + '); }</style>');
	} else if (type == "namespace") {
		document.write('<?import namespace="' + className + '" implementation="' + path_prefix + path + '" />');
	} else if (type == "text") {
		document.write('<script language="JavaScript">' + path + '</script>');
	}
};

YouShuAN.importLib = function(lib) {

	switch (lib) {
		case "kernel" : {
		    this._import("css", "public/products/blue-moon/css/bootstrap.min.css");
		    this._import("css", "public/products/blue-moon/css/new.css");
		    this._import("css", "public/products/blue-moon/css/charts-graphs.css");
		    this._import("css", "public/products/blue-moon/css/datepicker.css");
		    this._import("css", "public/products/blue-moon/fonts/font-awesome.min.css");
		    
		    this._import("js", "public/products/blue-moon/js/jquery.js");
		    this._import("js", "public/products/blue-moon/js/bootstrap.min.js");
		    this._import("js", "public/products/blue-moon/js/jquery.scrollUp.js");
		    this._import("js", "public/products/blue-moon/js/jquery-ui-v1.10.3.js");
		    this._import("js", "public/products/blue-moon/js/justgage/justgage.js");
		    this._import("js", "public/products/blue-moon/js/justgage/raphael.2.1.0.min.js");
		    this._import("js", "public/products/blue-moon/js/flot/jquery.flot.js");
		    this._import("js", "public/products/blue-moon/js/flot/jquery.flot.orderBar.min.js");
		    this._import("js", "public/products/blue-moon/js/flot/jquery.flot.stack.min.js");
		    this._import("js", "public/products/blue-moon/js/flot/jquery.flot.pie.min.js");
		    this._import("js", "public/products/blue-moon/js/flot/jquery.flot.tooltip.min.js");
		    this._import("js", "public/products/blue-moon/js/flot/jquery.flot.resize.min.js");
		    this._import("js", "public/products/blue-moon/js/menu.js");
		    break; 
		}
		case "jstree":{
			this._import("css", "public/products/plugins/jstree/dist/themes/default/style.min.css");
			this._import("js", "public/products/plugins/jstree/dist/jstree.min.js");
			this._import("js", "public/scripts/ui-tree.js");
			
		}
	}
};

(function() {
	YouShuAN.importLib('kernel');
	var metas = document.getElementsByTagName("META");
	for ( var i = 0; i < metas.length; i++) {
		if (metas[i].httpEquiv == "library") {
			var libraries = metas[i].content.split(",");
			for ( var i = 0; i < libraries.length; i++) {
				if (libraries[i] != "kernel") {
					YouShuAN.importLib(libraries[i]);
				}
			}
			break;
		}
	}
})();

function getWebPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 2);
    return (prePath + postPath);
}