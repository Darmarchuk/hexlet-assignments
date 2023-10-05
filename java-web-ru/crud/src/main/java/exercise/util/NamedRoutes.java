package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }


//    public static String postsByPage(String pageNum) {
//        return  "/posts?page="+pageNum;
//    }
//    public static String postsByPage(Integer pageNum) {
//        return postsByPage(String.valueOf(pageNum));
//    }

    public static String postById(Long id) {
        return postById(String.valueOf(id));
    }

    public static String postById(String id) {
        return "/posts/" + id;
    }

    public static String newPost() {
        return "/posts/build";
    }

    public static String editPost() {
        return "/posts/edit";
    }


    // END
}
