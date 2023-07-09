package uz.realsoft.task.presentation.mainflow.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.realsoft.task.R
import uz.realsoft.task.common.Constants.DONE
import uz.realsoft.task.common.Constants.IN_PROGRESS
import uz.realsoft.task.common.Constants.IN_REVIEW
import uz.realsoft.task.common.Constants.NEW
import uz.realsoft.task.databinding.LayoutStatusChangeBinding

class StatusChangeDialog(private val block: (Int) -> Unit) :
    BottomSheetDialogFragment(R.layout.layout_status_change) {

    private var _binding: LayoutStatusChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = LayoutStatusChangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            tvNew.setOnClickListener {
                block(NEW)
                dismissNow()
            }

            tvInProgress.setOnClickListener {
                block(IN_PROGRESS)
                dismissNow()
            }

            tvInReview.setOnClickListener {
                block(IN_REVIEW)
                dismissNow()
            }

            tvDone.setOnClickListener {
                block(DONE)
                dismissNow()
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}