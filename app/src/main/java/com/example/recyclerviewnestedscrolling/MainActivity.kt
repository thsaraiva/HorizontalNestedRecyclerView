package com.example.recyclerviewnestedscrolling

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State


class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val mAdapter = MyAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
                outRect.right = 25
            }
        })
    }

    class MyAdapter(private val context: Context) : RecyclerView.Adapter<MyAdapter.MovieViewHolder>() {

        private val movies = listOf(
            Movie("Titanic", "James Cameron", listOf("Leo Di Caprio", "Kate Winslet", "Billy Zane", "Kathy Bates")),
            Movie("Matrix", "Andy and Lana Wachowski", listOf("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss")),
            Movie("Jurassic Park", "Steven Spielberg", listOf("Sam Neill", "Laura Dern", "Jeff Goldblum", "Richard Attenborough")),
            Movie("Star Wars", "George Lucas", listOf("Mark Hamill", "Harrison Ford", "Carrie Fisher")),
            Movie("Pulp Fiction", "Quentin Tarantino", listOf("John Travolta", "Uma Thurman", "Samuel L. Jackson", "Bruce Willis", "Ving Rhames")),
            Movie(
                "Monty Python and The Holy Grail",
                "Terry Gilliam, Terry Jones",
                listOf("Graham Chapman", "John Cleese", "Eric Idle", "Terry Gilliam", "Terry Jones", "Michael Palin")
            )
        )


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MovieViewHolder(
                LayoutInflater.from(context).inflate(R.layout.movie_item_layout, parent, false)
            )


        override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) = viewHolder.bind(movies[position])

        override fun getItemCount() = movies.size


        class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val title = itemView.findViewById<TextView>(R.id.movie_title)
            private val directors = itemView.findViewById<TextView>(R.id.movie_directors)
            private val castContainer = itemView.findViewById<LinearLayout>(R.id.cast_container)

            fun bind(movie: Movie) {
                title.text = movie.title
                directors.text = movie.directors
//                movie.cast.forEach { actorName ->
//                    with(Button(itemView.context)) {
//                        text = actorName
//                        height = convertDpToPixelFloat(context, 72f)
//                        castContainer.addView(this)
//                    }
//                }
            }

            private fun convertDpToPixelFloat(context: Context, dp: Float): Int {
                return (dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
            }

        }
    }
}
