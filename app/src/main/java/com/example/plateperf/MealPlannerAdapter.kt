import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plateperf.Day
import com.example.plateperf.R

class MealPlannerAdapter(private var days: MutableList<Day>) : RecyclerView.Adapter<MealPlannerAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        val mealTextView: TextView = itemView.findViewById(R.id.mealTextView)
        val readyInMinutesTextView: TextView = itemView.findViewById(R.id.readyInMinutesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_planner, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val day = days[position]
//        Log.d("MealPlannerAdapter", "Day: $day")
//        holder.dayTextView.text = "Mo" // Assuming there is a name property in Day
//        holder.mealTextView.text = day.meals.joinToString("\n") { it.title }
//        holder.readyInMinutesTextView.text = day.meals.joinToString("\n") { it.readyInMinutes.toString() }

        // Set the day name
        holder.dayTextView.text = when (position) {
            0 -> "Monday"
            1 -> "Tuesday"
            2 -> "Wednesday"
            3 -> "Thursday"
            4 -> "Friday"
            5 -> "Saturday"
            6 -> "Sunday"
            else -> "Unknown"
        }

        // Join the titles of all meals for the day and set it to the mealTextView
        val mealTitles = day.meals.joinToString("\n") { it.title }
        holder.mealTextView.text = mealTitles

        // Join the ready in minutes of all meals for the day and set it to the readyInMinutesTextView
        val readyInMinutes = day.meals.joinToString("\n") { it.readyInMinutes.toString() }
        holder.readyInMinutesTextView.text = readyInMinutes
    }

    override fun getItemCount(): Int = days.size

    fun updateDays(newDays: List<Day>) {
        days.clear()
        days.addAll(newDays)
        notifyDataSetChanged()
    }
}
