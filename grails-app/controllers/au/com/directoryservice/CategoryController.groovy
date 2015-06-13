package au.com.directoryservice

class CategoryController extends AbstractController<Category> {

    static responseFormats = ['json', 'xml']

    CategoryController() {
        super(Category)
    }
}
