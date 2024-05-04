import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plateperf.Day
import com.example.plateperf.R

class MealPlannerAdapter(private var days: MutableList<Day>) :
    RecyclerView.Adapter<MealPlannerAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        val mealTableLayout: TableLayout = itemView.findViewById(R.id.mealTableLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_planner, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val day = days[position]

        // Set the day name
        val dayName = getStyledDayName(position)
        holder.dayTextView.text = dayName

        // Increase font size for day name
        holder.dayTextView.textSize = 20F

        // Clear existing rows from the table layout
        holder.mealTableLayout.removeAllViews()

        // Add each meal and its ready in minutes to the table layout
        day.meals.forEach { meal ->
            val mealRow = TableRow(holder.itemView.context)
            val mealNameTextView = TextView(holder.itemView.context)
            val readyInMinutesTextView = TextView(holder.itemView.context)

            mealNameTextView.text = meal.title
//            readyInMinutesTextView.text = meal.readyInMinutes.toString()

            // Increase font size for meal name and ready in minutes
            mealNameTextView.textSize = 16F
            mealNameTextView.setTextColor(holder.itemView.resources.getColor(android.R.color.black))
            readyInMinutesTextView.textSize = 16F
            readyInMinutesTextView.setPadding(20, 0, 0, 0)


            mealRow.addView(mealNameTextView)
            mealRow.addView(readyInMinutesTextView)

            holder.mealTableLayout.addView(mealRow)
        }
    }

    override fun getItemCount(): Int = days.size

    fun updateDays(newDays: List<Day>) {
        days.clear()
        days.addAll(newDays)
        notifyDataSetChanged()
    }

    private fun getStyledDayName(position: Int): SpannableString {
        val dayName = when (position) {
            0 -> "Monday"
            1 -> "Tuesday"
            2 -> "Wednesday"
            3 -> "Thursday"
            4 -> "Friday"
            5 -> "Saturday"
            6 -> "Sunday"
            else -> "Unknown"
        }
        val spannableString = SpannableString(dayName)

        // Apply bold and yellow color to days of the week
        if (position < 7) {
            spannableString.setSpan(
                StyleSpan(android.graphics.Typeface.BOLD),
                0,
                dayName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                ForegroundColorSpan(Color.parseColor("#FFC801")),
                0,
                dayName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            // Increase font size for days of the week
            spannableString.setSpan(
                RelativeSizeSpan(1.2F),
                0,
                dayName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannableString
    }
}
