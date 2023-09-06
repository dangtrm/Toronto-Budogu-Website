import React from 'react';
import APIService from '../APIService';

class productComponent extends React.Component {

    // The constructor() is invoked before the component is mounted. 
    //In the constructor, we have declared our product state variable
    constructor(props) {
        super(props)
    
        this.state = {
             products:[]
        }
    }

    // is called as soon as the component is mounted and ready. 
    //This is a good place to initiate API calls if you need to load data from a remote endpoint
    componentDidMount(){
        APIService.getProducts().then((data) => {
            this.setState({ products: data })
          })
          .catch(function (ex) {
              console.log('Response parsing failed. Error: ', ex);
          });
    }

    render() {
        return (
            <div>
                <h2>Product Details</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Product Info</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.products.map(product =>
                                    <tr key={product.id}>
                                        <td>{product.name}</td>
                                        <td>{product.info}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default productComponent();



