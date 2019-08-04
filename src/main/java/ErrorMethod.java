public class ErrorMethod {

    public Boolean sampleLogic(){

        try {
            subSampleLogic();
            throw new Exception("errrorrr");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // return true;
    }


    public Boolean subSampleLogic() throws Exception{
        throw new Exception("suberror");
    }

}
