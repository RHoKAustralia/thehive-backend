package au.com.directoryservice

class CategoryController extends AbstractController<Category> {

    static responseFormats = ['html', 'json', 'xml']

    CategoryController() {
        super(Category)
    }
}
