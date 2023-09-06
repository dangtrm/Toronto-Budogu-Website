const PRODUCTS_REST_API = 'http://localhost:8080/allProducts';
class APIService {    
    getProducts(){
        return fetch(PRODUCTS_REST_API,{ 
            method: 'get',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                }
        }).then((res => res.json()));        
    }

}

export default new APIService();