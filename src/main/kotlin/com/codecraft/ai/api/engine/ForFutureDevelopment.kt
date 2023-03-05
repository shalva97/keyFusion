package com.codecraft.ai.api.engine

//TODO for future development
/*
static("img") {
    staticRootFolder = File("C:\\SD_Dir")
    files(".")
}
post("/generateImage") {

    val postParams = call.receive<String>()
    println("Received json => $postParams")
    call.respond(Text2ImageRequest(prompt = "Some prompt"))
}

private suspend fun saveImageInMemory(text2ImageRequest: Text2ImageRequest, decodedImage: ByteArray) {
    val path = "/SD_Dir/${text2ImageRequest.uuid}"
    Files.createDirectories(Paths.get(path))

    val bufferedImage = ImageIO.read(ByteArrayInputStream(decodedImage))
    ImageIO.write(bufferedImage, "png", File("$path/Image_By_CodeCraft_SD.png"))
    //TODO we should send this url back to user:  http://127.0.0.1:8080/img/5a5b7fb1-853b-4d5f-aa3a-a56741a16946/Image_By_CodeCraft_SD.png
    //http://127.0.0.1:8080 this is standard url
    //endpoint is: /img
    //uuid: 5a5b7fb1-853b-4d5f-aa3a-a56741a16946
    //Image_By_CodeCraft_SD.png     this is name fo the image, we can append it or even create image with this url and we do not need to append enithing
}
*/