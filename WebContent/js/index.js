

function _tlsCheckboxSelAll(formObj,checkboxClicked){   
    var checkName = checkboxClicked.name;//取得点击的checkbox的name属性   
    var index = checkName.indexOf("_all");//判断是否有_all字符串   
    var isAll = false;   
    if(index!=-1){//如果有_all   
        if(index+"_all".length==checkName.length){//满足说明_all是在最后面，表示这是个全选/全不选的checkbox.   
            isAll = true;   
        }   
    }   
    var thisChecked = checkboxClicked.checked;//当前checkbox的状态 true=选中 false=不选中   
    if(isAll){//点了全选/全不选的checkbox   
        var childCheckName = checkName.substring(0,index);   
        eval("var allChildCheckboxs = formObj."+childCheckName);   
        var childCheckboxCount = allChildCheckboxs.length;   
        if(childCheckboxCount==null){//只有1个单个的checkbox   
            allChildCheckboxs.checked = thisChecked;   
        }else{//有2个或2个以上的单个checkbox   
            for(var i=0;i<childCheckboxCount;i++){   
                allChildCheckboxs[i].checked = thisChecked;   
            }   
        }   
    }else{//点了单个的checkbox   
        var parentCheckboxName = checkName+"_all";   
        if(thisChecked==false){   
            document.getElementById(parentCheckboxName).checked=false;   
        }else{//判断点了当前checkbox后是否全部的单个checkbox都处于true状态   
            eval("var allChildCheckboxs = formObj."+checkName);   
            var childCheckboxCount = allChildCheckboxs.length;   
            var isAllChecked = true;   
            if(childCheckboxCount==null){//只有1个单个的checkbox   
                if(allChildCheckboxs.checked==false){   
                    isAllChecked = false;   
                }   
            }else{//有2个或2个以上的单个checkbox   
                for(var i=0;i<childCheckboxCount;i++){   
                    if(allChildCheckboxs[i].checked==false){   
                        isAllChecked = false;   
                        break;   
                    }   
                }                  
            }   
            if(isAllChecked){//全部单个checkbox都处于true状态   
                document.getElementById(parentCheckboxName).checked=true;   
            }   
        }   
    }   
}  