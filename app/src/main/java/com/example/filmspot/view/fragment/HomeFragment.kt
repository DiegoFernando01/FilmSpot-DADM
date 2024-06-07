import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmspot.R
import com.example.filmspot.databinding.FragmentHomeBinding  // Asegúrate de usar el nombre correcto de tu archivo de binding
import com.example.filmspot.view.adapter.ReviewsAdapter
import com.example.filmspot.view.adapter.WatchListAdapter
import android.widget.TextView
import android.widget.ImageView
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.filmspot.network.RetrofitClient
import com.example.filmspot.model.MovieResponse


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var reviewsAdapter: ReviewsAdapter
    private lateinit var watchListAdapter: WatchListAdapter

    // Asegúrate de implementar ReviewsAdapter y WatchListAdapter correctamente

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inicializar los adapters
        reviewsAdapter = ReviewsAdapter(listOf()) // Asumiendo que tienes un constructor que toma una lista
        watchListAdapter = WatchListAdapter(listOf())

        // Configurar RecyclerViews
        view.findViewById<RecyclerView>(R.id.reviewsRecyclerView).adapter = reviewsAdapter
        view.findViewById<RecyclerView>(R.id.watchListRecyclerView).adapter = watchListAdapter

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        setupListeners()
    }

    private fun setupRecyclerViews() {
        RetrofitClient.tmdbApi.searchMovies("your_api_key_here", "query_to_search")
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    response.body()?.let {
                        reviewsAdapter = ReviewsAdapter(it.results)
                        binding.reviewsRecyclerView.adapter = reviewsAdapter
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("HomeFragment", "Error fetching movies", t)
                }
            })
    }


    private fun setupListeners() {
        binding.moreActivityText.setOnClickListener {
            // Handle the click event, possibly navigating to another fragment
        }
        binding.exploreButton.setOnClickListener {
            // Handle the click event
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class UserProfileFragment : Fragment() {

        private lateinit var db: FirebaseFirestore

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            db = Firebase.firestore
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Suponiendo que tienes un 'userId' disponible aquí
            val userId = "someUserId"

            db.collection("users").document(userId).get().addOnSuccessListener { document ->
                if (document != null) {
                    val usuario = document.toObject(Usuario::class.java)  // Asegúrate de tener una clase 'Usuario' que coincida con la estructura en Firestore
                    updateUI(usuario)
                } else {
                    Log.d("Firestore", "No such document")
                }
            }.addOnFailureListener { exception ->
                Log.d("Firestore", "get failed with ", exception)
            }
        }

        private fun updateUI(usuario: Usuario?) {
            // Verificar si el usuario no es null
            usuario?.let {
                binding.userName.text = it.nombre
                binding.favoriteMovieNameYear.text = "${it.peliculaFavorita.titulo} (${it.peliculaFavorita.año})"
                Glide.with(this).load(it.peliculaFavorita.urlImagen).into(binding.favoriteMovieImage)
                Glide.with(this).load(it.urlImagenPerfil).into(binding.userProfileImage)
            }
        }

        private fun loadFavoriteMovie() {
            RetrofitClient.instance.getMovieDetails("8c29c89cbf5f32d08bc73d76d2f70bc7")
                .enqueue(object : Callback<Movie> {
                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                binding.favoriteMovieTitle.text = it.title
                                binding.favoriteMovieNameYear.text = "${it.title} (${it.releaseDate})"
                                Glide.with(this@HomeFragment).load("https://image.tmdb.org/t/p/w500${it.posterPath}").into(binding.favoriteMovieImage)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        Log.e("HomeFragment", "Error fetching movie details", t)
                    }
                })
        }

    }


}
