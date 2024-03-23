
package com.example.android.thecafe

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

/**
 * This app displays an order form to order coffee.
 */
class MainActivity : AppCompatActivity() {
    private var quantity = 0
    private fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This method is called when the plus button is clicked.
     */
    fun increment(view: View?) {
        quantity += 1
        display(quantity)
    }

    /**
     * This method is called when the minus button is clicked.
     */
    fun decrement(view: View?) {
        if (quantity < 0) {
            return
        }
        quantity -= 1
        display(quantity)
    }

    /**
     * This method is called when the order button is clicked.
     */
    fun submitOrder(view: View?) {
        val nameField = findViewById(R.id.name_field) as EditText
        val nameEditable = nameField.getText()
        val name = nameEditable.toString()
        val whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox) as CheckBox
        val hasWhippedCream = whippedCreamCheckBox.isChecked
        val chocolateCheckBox = findViewById(R.id.chocolate_checkbox) as CheckBox
        val hasChocolate = chocolateCheckBox.isChecked
        val price = calculatePrice(hasWhippedCream, hasChocolate)
        createOrderSummary(name, price, hasWhippedCream, hasChocolate)
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not we should include whipped cream topping in the price
     * @param addChocolate    is whether or not we should include chocolate topping in the price
     * @return total price
     */
    private fun calculatePrice(addWhippedCream: Boolean, addChocolate: Boolean): Int {
        var basePrice = 5
        if (addWhippedCream) {
            basePrice += 1
        }
        if (addChocolate) {
            basePrice += 2
        }

        // Calculate the total order price by multiplying by the quantity
        return quantity * basePrice
    }

    /**
     * Create summary of the order.
     *
     * @param name            on the order
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private fun createOrderSummary(name: String, price: Int, addWhippedCream: Boolean,
                                   addChocolate: Boolean): String {
        var priceMessage = "Name:$name"
        priceMessage += "\n Add Whipped Cream?$addWhippedCream"
        priceMessage += "\n Add Chocolate?$addChocolate"
        priceMessage += "\n Quantity:$quantity"
        priceMessage += "\n Price:$price"
        priceMessage += "\n Thank You!"
        return priceMessage
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun display(numberOfCoffees: Int) {
        val quantityTextView = findViewById(
                R.id.quantity_text_view) as TextView

        quantityTextView.text = "$numberOfCoffees"
    }
}