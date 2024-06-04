import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmspot.databinding.FragmentHomeBinding  // Asegúrate de usar el nombre correcto de tu archivo de binding
import com.example.filmspot.adapters.ReviewsAdapter
import com.example.filmspot.adapters.WatchListAdapter


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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        setupListeners()
    }

    private fun setupRecyclerViews() {
        reviewsAdapter = ReviewsAdapter(listOf(/* sample data */))
        watchListAdapter = WatchListAdapter(listOf(/* sample data */))

        binding.reviewsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = reviewsAdapter
        }

        binding.watchListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = watchListAdapter
        }
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
}
