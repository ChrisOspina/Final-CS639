import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plateperf.Day
import com.example.plateperf.R

class MealPlannerAdapter(private var days: MutableList<Day>) : RecyclerView.Adapter<MealPlannerAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleMealPlannerTextView)
        val readyInMinutesTextView: TextView = itemView.findViewById(R.id.readyInMinutesTextView)
        val sourceUrlTextView: TextView = itemView.findViewById(R.id.sourceUrlTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_planner, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val day = days[position]
        holder.titleTextView.text = day.meals.joinToString("\n") { it.title }
        holder.readyInMinutesTextView.text = day.meals.joinToString("\n") { it.readyInMinutes.toString() }
        holder.sourceUrlTextView.text = day.meals.joinToString("\n") { it.sourceUrl }
    }

    override fun getItemCount(): Int = days.size

    fun updateDays(newDays: List<Day>) {
        days.clear()
        days.addAll(newDays)
        notifyDataSetChanged()
    }
}
