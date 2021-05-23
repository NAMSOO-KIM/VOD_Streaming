<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <meta charset="utf-8" />
    <!-- <script src="/Scripts/jquery-1.10.2.js"></script> -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://sdk.amazonaws.com/js/aws-sdk-2.911.0.min.js"></script>
	    <script type="text/javascript">
	    //d2q0hnzyjsk9p6.cloudfront.net
	 // Amazon Cognito 인증 공급자를 초기화합니다
	    AWS.config.region = 'us-east-1'; // 리전
	    AWS.config.credentials = new AWS.CognitoIdentityCredentials({
	        IdentityPoolId: 'us-east-1:94aeda81-d7a6-4b80-8552-5e600f986019',
	    });
	    </script>
    <script>

        $(function () {
            $("#fileUploadForm").submit(function () {
                
                var bucket = new AWS.S3({ params: { Bucket: 'nsvodbucket' } });
                var fileChooser = document.getElementById('file');
                var file = fileChooser.files[0];
				console.log(file.name);
                console.log(file.type);
                console.log(file);
                
                if (file) {
                	
                    var params = {
                       Key: file.name,
                       ContentType: file.type,
                       Body: file,
                       ACL: 'public-read' // 접근 권한한
                    };
					
                    bucket.putObject(params, function (err, data) {
                        // 업로드 성공
                        console.log("complete");
                        console.log("남수야 힘내~")
                    });

                    bucket.putObject(params).on('httpUploadProgress',
                        function (evt) {
                            console.log("Uploaded :: " + parseInt((evt.loaded * 100) / evt.total) + '%');
                        }).send(function (err, data) {
                            alert("File uploaded successfully.");
                        });
                } return false;
            });
        });
        
    </script>
</head>
<body>
    <form id="fileUploadForm" method="post" enctype="multipart/form-data">
        <input type="file" name="file" id="file" value="dataFile" required="">
        <input type="submit" value="Go" />
    </form>
</body>
</html>