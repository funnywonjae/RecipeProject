 function drawRecipe(data){
		    		$.each(data,function(a,b){
		    			
		    		    $(".all").append(                  		
		            			'<div class="col-md-3 col-sm-6" style="cursor:pointer" onclick='+
		            			'location.href="/recipe/?no='+data.recipe[i].recipe_no+'&page=readContent">'+

		                        '<div class="panel panel-default text-center">'+
		                            '<div class="panel-heading">'+
		        					'<div class="col-img-responsive02"><span>"'+data.recipe[i].recipe_title+'"</span></div>'+
		                             '<img src="'+data.thumbnail[i].thumbnail_path+'"  width="200px" height="200px" /></div>'+
		                            '<div class="panel-body">'+
		                                '<h4>'+data.recipe[i].recipe_title+'</h4>'+
		                                '<p>'+data.recipe[i].usr_id+'</p> </div> </div>'
		                                )

		    		
		    		});

		    	}