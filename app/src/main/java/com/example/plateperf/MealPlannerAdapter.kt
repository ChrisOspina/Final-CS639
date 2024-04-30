import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plateperf.Day
import com.example.plateperf.R

class MealPlannerAdapter(private var days: MutableList<Day>) : RecyclerView.Adapter<MealPlannerAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        val mealTableLayout: TableLayout = itemView.findViewById(R.id.mealTableLayout)
//        val readyInMinutesTextView: TextView = itemView.findViewById(R.id.readyInMinutesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_planner, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val day = days[position]

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

        // Clear existing rows from the table layout
        holder.mealTableLayout.removeAllViews()

        // Add each meal and its ready in minutes to the table layout
        day.meals.forEach { meal ->
            val mealRow = TableRow(holder.itemView.context)
            val mealNameTextView = TextView(holder.itemView.context)
            val readyInMinutesTextView = TextView(holder.itemView.context)

            mealNameTextView.text = meal.title
            readyInMinutesTextView.text = meal.readyInMinutes.toString()

            mealRow.addView(mealNameTextView)
            mealRow.addView(readyInMinutesTextView)

            holder.mealTableLayout.addView(mealRow)
        }

        // Set ready in minutes text
//        holder.readyInMinutesTextView.text = "Ready in Minutes: ${day.meals.joinToString { it.readyInMinutes.toString() }}"
    }

    override fun getItemCount(): Int = days.size

    fun updateDays(newDays: List<Day>) {
        days.clear()
        days.addAll(newDays)
        notifyDataSetChanged()
    }
}
