package com.example.rifqi.footballapp.features.Favorites


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.rifqi.footballapp.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.find

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private lateinit var viewPagerAdapter: FavoritePagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = FavoritePagerAdapter(context, fragmentManager!!)

        viewPager = find(R.id.view_pager_favorite)
        viewPager.adapter = viewPagerAdapter
        tab_layout_favorite.setupWithViewPager(view_pager_favorite)
    }

}
